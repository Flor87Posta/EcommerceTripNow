package com.tripnow.ecommerce.controllers;
import com.itextpdf.text.DocumentException;
import com.tripnow.ecommerce.Dto.OrdenDTO;
import com.tripnow.ecommerce.Dto.PagoDTO;
import com.tripnow.ecommerce.models.*;
import com.tripnow.ecommerce.services.ClienteServicio;
import com.tripnow.ecommerce.services.OrdenServicio;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
public class OrdenControlador {

    @Autowired
    OrdenServicio ordenServicio;
    @Autowired
    ClienteServicio clienteServicio;

    @Autowired
    EmailService emailService;

    @GetMapping("/api/clientes/orden")
    public List<OrdenDTO> obtenerOrdenesDTO(Authentication authentication ){
        Cliente cliente = clienteServicio.findByEmail(authentication.getName());
        List<OrdenDTO> ordenesActivas = cliente.getOrdenes()
                .stream()
                .filter(orden -> orden.isActiva())
                .map(orden -> new OrdenDTO(orden))
                .collect(Collectors.toList());
        return ordenesActivas;
    }

    @GetMapping("/api/clientes/ordenPaga")
    public List<OrdenDTO> obtenerOrdenesPagasDTO(Authentication authentication) {
        Cliente cliente = clienteServicio.findByEmail(authentication.getName());
        List<OrdenDTO> ordenesPagadas = cliente.getOrdenes()
                .stream()
                .filter(Orden::isPagada) // Filtrar órdenes pagadas
                .map(OrdenDTO::new)
                .collect(Collectors.toList());
        return ordenesPagadas;
    }

    @PostMapping("/api/clientes/current/orden")
    public ResponseEntity<Object> crearOrden (@RequestParam int cantidadPasajeros, Authentication authentication){

        Cliente cliente = clienteServicio.findByEmail(authentication.getName());

        if (cliente.getOrdenes().stream().filter( orden -> orden.isActiva()).collect(toList()).size() > 1){
            return new ResponseEntity<>("Ya tienes una orden en proceso", HttpStatus.FORBIDDEN);
        } else if (cliente.getOrdenes().stream().filter( orden -> orden.isActiva()).collect(toList()).size() == 1){
            return new ResponseEntity<>("Orden en proceso", HttpStatus.OK);}

        Orden nuevaOrden = new Orden(LocalDateTime.now(), true, cantidadPasajeros, 0, 0, false);
        cliente.añadirOrden(nuevaOrden);
        ordenServicio.saveOrden(nuevaOrden);
        return new ResponseEntity<>("Orden creada", HttpStatus.CREATED);
    }

    @PostMapping("/api/clientes/current/pagar-orden")
    public ResponseEntity<Object> pagarOrden(@RequestBody PagoDTO pagoDTO) {
        Cliente cliente = clienteServicio.findByEmail(pagoDTO.getEmail());
        List<Orden> ordenesPagas = cliente.getOrdenes().stream().filter(Orden::isPagada).collect(Collectors.toUnmodifiableList());
        Orden orden = ordenServicio.findById(pagoDTO.getIdOrden());
        if (ordenesPagas.isEmpty()) {
            String numeroTarjeta = pagoDTO.getNumber();
            int cvv = pagoDTO.getCvv();
            double monto = pagoDTO.getAmount();
            String descripcion = pagoDTO.getDescription();
            FormaPago formaPago = pagoDTO.getTypeCard();
            String email = pagoDTO.getEmail();

            if (monto < 0) {
                return new ResponseEntity<>("El monto no debe ser negativo", HttpStatus.FORBIDDEN);
            }
            if (monto < orden.getPrecioTotalOrden()) {
                return new ResponseEntity<>("El monto no puede ser menor al precio total de la orden", HttpStatus.FORBIDDEN);
            }
            if (descripcion.isBlank()) {
                return new ResponseEntity<>("Debes proporcionar una descripción", HttpStatus.FORBIDDEN);
            }
            if (numeroTarjeta.isBlank()) {
                return new ResponseEntity<>("Debes proporcionar un número de tarjeta", HttpStatus.FORBIDDEN);
            }
            if (numeroTarjeta.length() != 19) {
                return new ResponseEntity<>("Debes proporcionar un número de tarjeta válido", HttpStatus.FORBIDDEN);
            }
            if (email.isBlank()) {
                return new ResponseEntity<>("Debes proporcionar un email", HttpStatus.FORBIDDEN);
            }
            if (formaPago == null) {
                return new ResponseEntity<>("Debes elegir un tipo de tarjeta", HttpStatus.FORBIDDEN);
            }

            try {
                URL url = new URL("https://homebanking-mindhub-brothers.up.railway.app/api/clients/current/pay-card");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                String cuerpoDeSolicitud = "{\"cvv\": " + cvv + ", \"amount\": " + monto + ", \"number\": \"" + numeroTarjeta + "\", \"description\": \"" + descripcion + "\", \"email\": \"" + email + "\", \"typeCard\": \"" + formaPago + "\"}";
                connection.getOutputStream().write(cuerpoDeSolicitud.getBytes());

                int codigoDeRespuesta = connection.getResponseCode();
                System.out.println("Respuesta del servidor: " + codigoDeRespuesta);
                if (codigoDeRespuesta == HttpURLConnection.HTTP_CREATED) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String response = reader.readLine();
                    System.out.println("Respuesta del servidor: " + response);

                    connection.getInputStream().close();
                    connection.disconnect();
                    orden.setPagada(true);
                    orden.setActiva(false);
                    ordenServicio.saveOrden(orden);

                    return new ResponseEntity<>("Pago aceptado", HttpStatus.CREATED);
                } else {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String response = reader.readLine();
                    System.out.println("Respuesta del servidor: " + response);

                    connection.getInputStream().close();
                    connection.disconnect();

                    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
                }
            } catch (Exception err) {
                err.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al realizar el pago");
            }

        }
        return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/api/clientes/current/export-pdf")
    public ResponseEntity<Object> ExportingToPDF(HttpServletResponse response, Authentication authentication, @RequestParam long idOrden) throws DocumentException, IOException {

        Cliente cliente = clienteServicio.findByEmail(authentication.getName());
        Orden orden = ordenServicio.findById(idOrden);

        if(cliente == null){
            return new ResponseEntity<>("Tu no eres un cliente", HttpStatus.FORBIDDEN);
        }

        if (orden == null) {
            return new ResponseEntity<>("Id de orden inválido", HttpStatus.FORBIDDEN);
        }

        if(orden.isPagada()){

        // Generar el archivo PDF en memoria
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        OrdenPDFExporter ordenExporter = new OrdenPDFExporter(cliente, orden);
        ordenExporter.usePDFExport(pdfOutputStream);

        // Enviar el archivo PDF por correo electrónico al cliente
        emailService.sendConfirmationEmail(cliente.getEmail(), "token_de_confirmacion", pdfOutputStream);}

        return new ResponseEntity<>("PDF is created and sent", HttpStatus.CREATED);
    }

}
