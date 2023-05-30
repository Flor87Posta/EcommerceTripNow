package com.tripnow.ecommerce.Dto;

import com.tripnow.ecommerce.models.Excursion;

public class ExcursionDTO {
    private long id;
    private String nombre;
    private String actividad;
    private double precioExcursion;
    private int cantidadStock;

    public ExcursionDTO() {
    }
    public ExcursionDTO(Excursion excursion){
        this.id = excursion.getId();
        this.nombre = excursion.getNombre();
        this.actividad = excursion.getActividad();
        this.precioExcursion = excursion.getPrecioExcursion();
        this.cantidadStock = excursion.getCantidadStock();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public double getPrecioExcursion() {
        return precioExcursion;
    }

    public void setPrecioExcursion(double precioExcursion) {
        this.precioExcursion = precioExcursion;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }
}
