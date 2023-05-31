package com.tripnow.ecommerce.Dto;

import com.tripnow.ecommerce.models.Cliente;
import com.tripnow.ecommerce.models.Orden;
import com.tripnow.ecommerce.models.Paquete;

public class PaqueteSeleccionadoDTO {

    private long id;
    private String nombrePaquete;
    private int dias;


    public PaqueteSeleccionadoDTO(){};
    public PaqueteSeleccionadoDTO(Paquete paquete) {
        Orden orden = paquete.getOrdenes().iterator().next(); // Obtener una de las órdenes asociadas al paquete
        Cliente cliente = orden.getCliente(); // Obtener el cliente de la orden

        this.id = cliente.getId();
        this.nombrePaquete = paquete.getNombrePaquete();
        this.dias = paquete.getDias();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
}
