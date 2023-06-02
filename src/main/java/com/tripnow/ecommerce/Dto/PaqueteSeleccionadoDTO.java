package com.tripnow.ecommerce.Dto;

import com.tripnow.ecommerce.models.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PaqueteSeleccionadoDTO {

    private long id;
    private String nombrePaquete;
    private int dias;
    private Hotel hotel;
    private Destino destino;
    private Excursion excursion;
    private Pasaje pasaje;



    public PaqueteSeleccionadoDTO(){};
    public PaqueteSeleccionadoDTO(Paquete paquete) {
        Orden orden = paquete.getOrdenes().iterator().next(); // Obtener una de las órdenes asociadas al paquete
        Cliente cliente = orden.getCliente(); // Obtener el cliente de la orden

        this.id = cliente.getId();
        this.nombrePaquete = paquete.getNombrePaquete();
        this.dias = paquete.getDias();
        this.hotel = hotel;
        this.destino = destino;
        this.excursion = excursion;
        this.pasaje = pasaje;

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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public Pasaje getPasaje() {
        return pasaje;
    }

    public void setPasaje(Pasaje pasaje) {
        this.pasaje = pasaje;
    }
}
