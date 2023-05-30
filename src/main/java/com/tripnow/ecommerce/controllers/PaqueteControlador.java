package com.tripnow.ecommerce.controllers;
import com.tripnow.ecommerce.Dto.PaqueteDTO;
import com.tripnow.ecommerce.Dto.PaqueteSeleccionadoDTO;
import com.tripnow.ecommerce.models.*;
import com.tripnow.ecommerce.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Autowired
    private ExcursionServicio excursionServicio;

    @Autowired
    private HotelServicio hotelServicio;

    @GetMapping("/api/paquete")
    public List<PaqueteDTO> obtenerPaquetes() {
        return paqueteServicio.getPaquetesDTO();
    }

    @PostMapping("/api/cliente/agregar-paquete")
    public ResponseEntity<Object> añadirPaquete(@RequestBody PaqueteSeleccionadoDTO paqueteSeleccionadoDTO) {
        Cliente cliente = clienteServicio.findById(paqueteSeleccionadoDTO.getId());
        if (cliente == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        List<Orden> ordenes = cliente.getOrdenes().stream()
                .filter(orden -> orden.getPaquetes().isEmpty())
                .collect(toList());

        if (ordenes.isEmpty()) {
            return new ResponseEntity<>("No tienes una orden activa o ya tienes paquetes asignados", HttpStatus.FORBIDDEN);
        }

        Orden orden = ordenes.get(0);
        Destino destino = destinoServicio.getDestino(paqueteSeleccionadoDTO.getId());
        Pasaje pasaje = pasajeServicio.getPasaje(paqueteSeleccionadoDTO.getId());

        if (destino == null || pasaje == null) {
            return new ResponseEntity<>("Destino o pasaje no encontrado", HttpStatus.NOT_FOUND);
        }

        Paquete nuevoPaquete = new Paquete(paqueteSeleccionadoDTO.getNombrePaquete(), paqueteSeleccionadoDTO.getDias(), destino.getPrecioHotelExcursion() + pasaje.getPrecioPasaje());
        nuevoPaquete.setDestino(destino);
        nuevoPaquete.setPasaje(pasaje);

        paqueteServicio.savePaquete(nuevoPaquete);

        // Para Disminuir la cantidad de stock de los hoteles
        Set<Hotel> hoteles = destino.getHoteles();
        for (Hotel hotel : hoteles) {
            hotel.setCantidadStock(hotel.getCantidadStock() - 1);
            hotelServicio.saveHotel(hotel);
        }

        // Para Disminuir la cantidad de stock de las excursiones
        Set<Excursion> excursiones = destino.getExcursiones();
        for (Excursion excursion : excursiones) {
            excursion.setCantidadStock(excursion.getCantidadStock() - 1);
            excursionServicio.saveExcursion(excursion);
        }

        // Disminuir la cantidad de stock del pasaje
        pasaje.setCantidadStock(pasaje.getCantidadStock() - 1);
        pasajeServicio.savePasaje(pasaje);

        orden.añadirPaquete(nuevoPaquete);
        orden.setCantidadPasajeros(orden.getCantidadPasajeros());
        orden.setPrecioTotalPaquete(nuevoPaquete.getPrecioTotalUnitario());
        orden.setPrecioTotalOrden(orden.getPrecioTotalOrden() + nuevoPaquete.getPrecioTotalUnitario());
        orden.setPrecioTotalOrden(orden.getPrecioTotalOrden() * orden.getCantidadPasajeros());

        ordenServicio.saveOrden(orden);

        return new ResponseEntity<>("Paquete añadido", HttpStatus.CREATED);
    }
    //En el método añadirPaquete, primero verifico si el cliente existe. Luego, filtro las órdenes activas del
    // cliente y compruebo si hay alguna disponible. Si no hay ninguna orden activa, se devuelve una respuesta de error.
    //Luego, creo un nuevo objeto Paquete utilizando los datos proporcionados en el PaqueteDTO.
    // Establecemos el destino y el pasaje correspondientes y lo guardamos utilizando el servicio PaqueteServicio.
    //Luego, añadimos el paquete a la orden, actualizamos la cantidad de pasajeros y el precio total de la orden, y los stock;
    // y guardamos la orden utilizando el servicio OrdenServicio.
    //Y al final, se devuelve una respuesta exitosa.
}