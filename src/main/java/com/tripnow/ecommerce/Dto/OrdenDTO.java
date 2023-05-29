package com.tripnow.ecommerce.Dto;
import com.tripnow.ecommerce.models.Orden;
import java.time.LocalDateTime;

public class OrdenDTO {
    private long id;
    private LocalDateTime fechaCreacion;
    private boolean activa;
    private int cantidadPasajeros;
    private double precioTotalPaquetes;
    private boolean pagada;
    public OrdenDTO() {
    }
    public OrdenDTO(Orden orden){
        this.id = orden.getId();
        this.fechaCreacion = orden.getFechaCreacion();
        this.activa = orden.isActiva();
        this.cantidadPasajeros = orden.getCantidadPasajeros();
        this.precioTotalPaquetes = orden.getPrecioTotalPaquetes();
        this.pagada = orden.isPagada();

    }

    public long getId() {
        return id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public boolean isActiva() {
        return activa;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public double getPrecioTotalPaquetes() {
        return precioTotalPaquetes;
    }

    public boolean isPagada() {
        return pagada;
    }
}

