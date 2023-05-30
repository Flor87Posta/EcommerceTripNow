package com.tripnow.ecommerce.Dto;

import com.tripnow.ecommerce.models.Paquete;

public class PaqueteDTO {

    private long id;
    private String nombrePaquete;
    private int dias;
    private double precioTotalUnitario;

    public PaqueteDTO(){};
    public PaqueteDTO(Paquete paquete) {
        this.id = paquete.getId();
        this.nombrePaquete = paquete.getNombrePaquete();
        this.dias = paquete.getDias();
        this.precioTotalUnitario = paquete.getPrecioTotalUnitario();
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

    public double getPrecioTotalUnitario() {
        return precioTotalUnitario;
    }

    public void setPrecioTotalUnitario(double precioTotalUnitario) {
        this.precioTotalUnitario = precioTotalUnitario;
    }
}
