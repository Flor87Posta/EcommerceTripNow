package com.tripnow.ecommerce.controllers;
import com.itextpdf.text.DocumentException;
import com.tripnow.ecommerce.Dto.OrdenDTO;
import com.tripnow.ecommerce.models.Cliente;
import com.tripnow.ecommerce.models.Orden;
import com.tripnow.ecommerce.models.OrdenPDFExporter;
import com.tripnow.ecommerce.services.ClienteServicio;
import com.tripnow.ecommerce.services.OrdenServicio;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class OrdenControlador {

    @Autowired
    OrdenServicio ordenServicio;
    @Autowired
    ClienteServicio clienteServicio;

    @GetMapping("/api/clientes/orden/{id}")
    public List<OrdenDTO> obtenerOrdenesDTO(@PathVariable Long id ){
        Cliente clientes = clienteServicio.findById(id);
        List<OrdenDTO> ordenes = clientes.getOrdenes().stream().map(OrdenDTO::new).collect(toList());
        return ordenes;
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
    /*@Transactional
    @PostMapping("/api/clientes/current/pagar-orden")
    public ResponseEntity<Object> pagarOrden(@RequestBody PagoDTO pagoDTO){

        Cliente cliente = clienteServicio.findByEmail(pagoDTO.getEmail());
hacer condicional con el is.pagada antes para ver q sea false y recien ahi siga toda la logica
        if (!EnumSet.of(FormaPago.CREDITO, FormaPago.DEBITO).contains(paymentDTO.getTypeCard())) {
            return new ResponseEntity<>("Invalid card type", HttpStatus.FORBIDDEN);
        }

        if(!cardService.existsByNumber(paymentDTO.getNumber())){
            return new ResponseEntity<>("Invalid card", HttpStatus.FORBIDDEN);
        }

        Card cardUsed = cardService.findByNumber(paymentDTO.getNumber());

        boolean hasCard = client.getCards()
                .stream()
                .anyMatch(card -> card.getId() == cardUsed.getId());

        if(!hasCard){
            return new ResponseEntity<>("You don't have this card", HttpStatus.FORBIDDEN);
        }

        if(cardUsed.getThruDate().isBefore(LocalDate.of(2023, 5, 16))){
            return new ResponseEntity<>("This card is expired", HttpStatus.FORBIDDEN);
        }

        Optional<Account> optionalAccountToBeDebited = client.getAccountSet()
                .stream()
                .filter(acc -> acc.getBalance() >= paymentDTO.getAmount())
                .findFirst();

        if (optionalAccountToBeDebited.isPresent()) {
            Account accountToBeDebited = optionalAccountToBeDebited.get();
            // Realizo las operaciones con la cuenta encontrada


            Double initialBalanceaccountToBeDebited = accountToBeDebited.getBalance() - paymentDTO.getAmount();
            Transaction paymentCard = new Transaction(TransactionType.DEBIT, paymentDTO.getAmount(), paymentDTO.getDescription(), LocalDateTime.now(),initialBalanceaccountToBeDebited );
            transactionService.saveNewTransaction(paymentCard);
            accountToBeDebited.addTransaction(paymentCard);
            double newBalanceDebit = accountToBeDebited.getBalance() - paymentDTO.getAmount(); // Calcula el nuevo saldo
            accountToBeDebited.setBalance(newBalanceDebit); // Actualizo el saldo
            accountService.saveNewAccount(accountToBeDebited); //guardo la cuenta

            return new ResponseEntity<>("Payment successful", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Insufficient Funds", HttpStatus.CREATED);
        }
    }*/

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

        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Transactions" + idOrden + ".pdf";
        response.setHeader(headerKey, headerValue);


       OrdenPDFExporter ordenExporter = new OrdenPDFExporter(cliente, orden);
        ordenExporter.usePDFExport(response); // Genera el archivo PDF y envíalo como respuesta

        return new ResponseEntity<>("PDF is created", HttpStatus.CREATED);
    }

}
