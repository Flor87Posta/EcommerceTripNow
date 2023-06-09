package com.tripnow.ecommerce.controllers;
import com.tripnow.ecommerce.Dto.PaqueteDTO;
import com.tripnow.ecommerce.Dto.PaqueteSeleccionadoDTO;
import com.tripnow.ecommerce.models.*;
import com.tripnow.ecommerce.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
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

    @GetMapping("/api/paquetes")
    public List<PaqueteDTO> obtenerPaquetes() {
        return paqueteServicio.getPaquetesDTO();
    }


    @PostMapping("/api/clientes/current/seleccionar-paquete")
    public ResponseEntity<Object> seleccionarPaquete(@RequestParam Long idPaquete, Authentication authentication) {
        Cliente cliente = clienteServicio.findByEmail(authentication.getName());
        Paquete paquete = paqueteServicio.findById(idPaquete);

        if (cliente == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        if (paquete == null) {
            return new ResponseEntity<>("Paquete no encontrado", HttpStatus.NOT_FOUND);
        }

        List<Orden> ordenes = cliente.getOrdenes().stream()
                .filter(orden -> orden.isActiva())
                .collect(toList());

        if (ordenes.isEmpty() || !ordenes.get(0).isActiva()) {
            return new ResponseEntity<>("No tienes una orden activa", HttpStatus.FORBIDDEN);
        }

        Orden orden = ordenes.get(0);
        orden.añadirPaquete(paquete);
        orden.setPrecioTotalPaquete(paquete.getPrecioTotalUnitario());
        orden.setPrecioTotalOrden(orden.getPrecioTotalOrden() + (paquete.getPrecioTotalUnitario() * orden.getCantidadPasajeros()));

        // Disminuir el stock del paquete
        int stockActual = paquete.getStock();
        if (stockActual > 0) {
            int cantidadPasajeros = orden.getCantidadPasajeros();
            if (stockActual >= cantidadPasajeros) {
                paquete.setStock(stockActual - cantidadPasajeros);
                ordenServicio.saveOrden(orden);
                paqueteServicio.savePaquete(paquete);
            } else {
                return new ResponseEntity<>("No hay suficiente stock disponible para la cantidad de pasajeros", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("No hay stock disponible para el paquete seleccionado", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Paquete añadido a la orden", HttpStatus.CREATED);
    }

    @PostMapping("/api/clientes/current/agregar-paquete")
    public ResponseEntity<Object> añadirPaquete(@RequestBody PaqueteSeleccionadoDTO paqueteSeleccionadoDTO) {
        Cliente cliente = clienteServicio.findById(paqueteSeleccionadoDTO.getId());
        if (cliente == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        List<Orden> ordenes = cliente.getOrdenes().stream()
                .filter(orden -> orden.isActiva())
                .collect(toList());

        if (!ordenes.get(0).isActiva()) {
            return new ResponseEntity<>("No tienes una orden activa", HttpStatus.FORBIDDEN);
        }

        Orden orden = ordenes.get(0);
        Destino destino = destinoServicio.getDestino(paqueteSeleccionadoDTO.getId());
        Pasaje pasaje = pasajeServicio.getPasaje(paqueteSeleccionadoDTO.getId());

        if (destino == null || pasaje == null) {
            return new ResponseEntity<>("Destino o pasaje no encontrado", HttpStatus.NOT_FOUND);
        }

        Paquete nuevoPaquete = new Paquete(paqueteSeleccionadoDTO.getNombrePaquete(), paqueteSeleccionadoDTO.getDias(), destino.getPrecioHotelExcursion() + pasaje.getPrecioPasaje(), 10, "/assets/hotel.jpg", destino.getHoteles());
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
//        orden.setCantidadPasajeros(orden.getCantidadPasajeros());
        orden.setPrecioTotalPaquete(nuevoPaquete.getPrecioTotalUnitario());
        orden.setPrecioTotalOrden(orden.getPrecioTotalOrden() + (nuevoPaquete.getPrecioTotalUnitario()* orden.getCantidadPasajeros()));


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

    //con logica similar hago el eliminar paquetes:
    @PostMapping("/api/clientes/current/eliminar-paquete")
    public ResponseEntity<Object> eliminarPaquete(@RequestParam Long idPaquete, Authentication authentication) {
        Cliente cliente = clienteServicio.findByEmail(authentication.getName());
        Paquete paquete = paqueteServicio.findById(idPaquete);

        if (cliente == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        if (paquete == null) {
            return new ResponseEntity<>("Paquete no encontrado", HttpStatus.NOT_FOUND);
        }

        List<Orden> ordenes = cliente.getOrdenes().stream()
                .filter(orden -> orden.isActiva())
                .collect(toList());

        if (ordenes.isEmpty() || !ordenes.get(0).isActiva()) {
            return new ResponseEntity<>("No tienes una orden activa", HttpStatus.FORBIDDEN);
        }

        Orden orden = ordenes.get(0);
        boolean paqueteEliminado = orden.eliminarPaquete(paquete);

        if (paqueteEliminado) {
            // Incrementar el stock del paquete
            int stockActual = paquete.getStock();
            paquete.setStock(stockActual + 1);
            paqueteServicio.savePaquete(paquete);

            // Restar el precio del paquete eliminado al precio total de la orden
            double precioPaqueteEliminado = paquete.getPrecioTotalUnitario();
            int cantidadPasajeros = orden.getCantidadPasajeros();
            double precioTotalOrden = orden.getPrecioTotalOrden();
            orden.setPrecioTotalOrden(precioTotalOrden - (precioPaqueteEliminado * cantidadPasajeros));

            ordenServicio.saveOrden(orden);

            return new ResponseEntity<>("Paquete eliminado de la orden", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("El paquete no está presente en la orden", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/api/admin/paquete")
    public ResponseEntity<Object> crearPaquete(@RequestBody Paquete paquete) {
        // Obtén las instancias correspondientes de destino y pasaje
        Destino destino = destinoServicio.getDestino(paquete.getDestino().getId());
        Pasaje pasaje = pasajeServicio.getPasaje(paquete.getPasaje().getId());

        // Verifica que las instancias de destino y pasaje existan
        if (destino == null || pasaje == null) {
            return new ResponseEntity<>("El destino o pasaje especificado no existe", HttpStatus.BAD_REQUEST);
        }

        // Asigna las instancias de destino y pasaje al paquete
        paquete.setDestino(destino);
        paquete.setPasaje(pasaje);

        // Guarda el paquete en la base de datos
        paqueteServicio.savePaquete(paquete);

        return new ResponseEntity<>("Paquete creado con éxito", HttpStatus.CREATED);
    }
    @PostMapping("/api/admin/destino")
    public ResponseEntity<Object>crearDestino(@RequestBody Destino destino){
        destinoServicio.saveDestino(destino);
        return new ResponseEntity<>("Destino creado con éxito", HttpStatus.CREATED);
    }
    @PostMapping("/api/admin/hotel")
    public ResponseEntity<Object>crearHotel(@RequestBody Hotel hotel){
        Destino destino = destinoServicio.getDestino(hotel.getDestino().getId());
        if (destino == null) {
            return new ResponseEntity<>("El destino o pasaje especificado no existe", HttpStatus.BAD_REQUEST);
        }
        hotelServicio.saveHotel(hotel);
        return new ResponseEntity<>("Hotel creado con éxito", HttpStatus.CREATED);
    }
    @PostMapping("/api/admin/excursion")
    public ResponseEntity<Object>crearExcursion(@RequestBody Excursion excursion){
        Destino destino = destinoServicio.getDestino(excursion.getDestino().getId());
        if (destino == null) {
            return new ResponseEntity<>("El destino o pasaje especificado no existe", HttpStatus.BAD_REQUEST);
        }
        excursionServicio.saveExcursion(excursion);
        return new ResponseEntity<>("Excursion creada con éxito", HttpStatus.CREATED);
    }
}
