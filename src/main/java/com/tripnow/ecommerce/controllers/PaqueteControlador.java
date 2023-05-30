package com.tripnow.ecommerce.controllers;
import com.tripnow.ecommerce.Dto.PaqueteDTO;
import com.tripnow.ecommerce.models.Cliente;
import com.tripnow.ecommerce.models.Orden;
import com.tripnow.ecommerce.models.Paquete;
import com.tripnow.ecommerce.models.Destino;
import com.tripnow.ecommerce.models.Pasaje;
import com.tripnow.ecommerce.services.ClienteServicio;
import com.tripnow.ecommerce.services.OrdenServicio;
import com.tripnow.ecommerce.services.PaqueteServicio;
import com.tripnow.ecommerce.services.DestinoServicio;
import com.tripnow.ecommerce.services.PasajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;


@RestController
public class PaqueteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private OrdenServicio ordenServicio;

    @Autowired
    private PaqueteServicio paqueteServicio;

    @Autowired
    private DestinoServicio destinoServicio;

    @Autowired
    private PasajeServicio pasajeServicio;

    @GetMapping("/api/paquete")
    public List<PaqueteDTO> obtenerPaquetes() {
        return paqueteServicio.getPaquetesDTO();
    }

    @PostMapping("/api/cliente/agregar-paquete")
    public ResponseEntity<Object> añadirPaquete(@RequestBody PaqueteDTO paqueteDTO) {

        Cliente cliente = clienteServicio.findById(paqueteDTO.getId());
        if (cliente==null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        List<Orden> ordenes = cliente.getOrdenes().stream()
                .filter(orden -> orden.getPaquetes().isEmpty()) // Verifico si la orden no tiene paquetes asignados
                .collect(toList());

        if (ordenes.isEmpty()) {
            return new ResponseEntity<>("No tienes una orden activa o ya tienes paquetes asignados", HttpStatus.FORBIDDEN);
        }

        Orden orden = ordenes.get(0);
        Destino destino = destinoServicio.getDestino(paqueteDTO.getId());
        Pasaje pasaje = pasajeServicio.getPasaje(paqueteDTO.getId());

        if (destino == null || pasaje == null) {
            return new ResponseEntity<>("Destino o pasaje no encontrado", HttpStatus.NOT_FOUND);
        }

        Paquete nuevoPaquete = new Paquete(paqueteDTO.getNombrePaquete(), paqueteDTO.getDias(), destino.getPrecioHotelExcursion() + pasaje.getPrecioPasaje());
        nuevoPaquete.setDestino(destino);
        nuevoPaquete.setPasaje(pasaje);

        paqueteServicio.savePaquete(nuevoPaquete);

        orden.añadirPaquete(nuevoPaquete);
        orden.setCantidadPasajeros(orden.getCantidadPasajeros());
        orden.setPrecioTotalPaquete(nuevoPaquete.getPrecioTotalUnitario());
        orden.setPrecioTotalOrden(orden.getPrecioTotalOrden() + nuevoPaquete.getPrecioTotalUnitario());
        // Multiplico aca el precio total del paquete por la cantidad de pasajeros
        orden.setPrecioTotalOrden(orden.getPrecioTotalOrden() * orden.getCantidadPasajeros());

        ordenServicio.saveOrden(orden);

        return new ResponseEntity<>("Paquete añadido", HttpStatus.CREATED);
    }
    //En el método añadirPaquete, primero verifico si el cliente existe. Luego, filtro las órdenes activas del
    // cliente y compruebo si hay alguna disponible. Si no hay ninguna orden activa, se devuelve una respuesta de error.
    //Luego, creo un nuevo objeto Paquete utilizando los datos proporcionados en el PaqueteDTO.
    // Establecemos el destino y el pasaje correspondientes y lo guardamos utilizando el servicio PaqueteServicio.
    //Luego, añadimos el paquete a la orden, actualizamos la cantidad de pasajeros y el precio total de la orden,
    // y guardamos la orden utilizando el servicio OrdenServicio.
    //Y al final, se devuelve una respuesta exitosa.
}
