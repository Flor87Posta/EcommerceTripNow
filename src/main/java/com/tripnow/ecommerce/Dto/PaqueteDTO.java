package com.tripnow.ecommerce.Dto;

import com.tripnow.ecommerce.models.Paquete;

public class PaqueteDTO {

    private long id;
    private String nombrePaquete;
    private int dias;
    private double precioTotalUnitario;
    private int stock;
    private String imagen1;
    private String imagen2;
    private String imagen3;


    public PaqueteDTO(){};
    public PaqueteDTO(Paquete paquete) {
        this.id = paquete.getId();
        this.nombrePaquete = paquete.getNombrePaquete();
        this.dias = paquete.getDias();
        this.precioTotalUnitario = paquete.getPrecioTotalUnitario();
        this.stock = paquete.getStock();
        this.imagen1=paquete.getImagen1();
        this.imagen2=paquete.getImagen2();
        this.imagen3=paquete.getImagen3();
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen1() {
        return imagen1;
    }

    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen3() {
        return imagen3;
    }

    public void setImagen3(String imagen3) {
        this.imagen3 = imagen3;
    }
}
