package com.tripnow.ecommerce.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaqueteControlador {

 /*   @GetMapping("/api/paquete")
    public List<PaqueteDTO> obtenerPaquetes() {
        return PaqueteServicio.obtenerPaquetesDTO();
    }*/

/*    @PostMapping("/api/cliente/agregar-paquete") //VER EL TEMA DEL STOCK.. SE MANEJA DESDE ACA O
DESDE LOS CONTROLADORES DE DESTINO Y PASAJES?
    public ResponseEntity<Object> añadirPaquete(@RequestBody PaqueteDTO paqueteDTO){

        Cliente cliente = clienteServicio.findById(paqueteDTO.getIdCliente());
        List<Orden> ordenes = cliente.getOrdenes().stream().filter( ordenPagada -> !ordenPagada.isComprado()).collect(toList());
        Orden orden = ordenes.get(0);
        Destino destino = destinoServicio.getDestino(paqueteDTO.getId());
        //hacer igual para pasaje


        if( orden == null ){
            return new ResponseEntity<>("Ya tienes una orden" , HttpStatus.FORBIDDEN);
        }

        Paquete nuevoPaquete = new Paquete(paqueteDTO.getDestino(), destino.getPrecio()+pasaje.getPrecio(),....);
        paquete?.añadirPaquete(nuevoPaquete);
        orden.añadirPaquete(nuevoPaquete);
        paqueteServicio.savePaquete(nuevoPaquete);

        orden.setUnidadesTotales(orden.getUnidadesTotales() + PaqueteDTO);
        orden.setPrecioTotal(orden.getPrecioTotal() + paquete.getPrecio()+pasaje.getPrecio().getcantidadPasajeros());
        ordenServicios.saveOrden(orden);

        return new ResponseEntity<>("Paquete añadido", HttpStatus.CREATED);
    }*/
}
