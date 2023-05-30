package com.tripnow.ecommerce.Dto;
import com.tripnow.ecommerce.models.Orden;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenDTO {
    private long id;
    private LocalDateTime fechaCreacion;
    private boolean activa;
    private int cantidadPasajeros;
    private double precioTotalPaquete;

    private double precioTotalOrden;
    private boolean pagada;

    private List<PaqueteDTO> paquetesDTO;

    public OrdenDTO() {
    }
    public OrdenDTO(Orden orden){
        this.id = orden.getId();
        this.fechaCreacion = orden.getFechaCreacion();
        this.activa = orden.isActiva();
        this.cantidadPasajeros = orden.getCantidadPasajeros();
        this.precioTotalPaquete = orden.getPrecioTotalPaquete();
        this.precioTotalOrden= orden.getPrecioTotalOrden();
        this.pagada = orden.isPagada();
        this.paquetesDTO = orden.getPaquetes()
                .stream()
                .map(paquete -> new PaqueteDTO(paquete))
                .collect(Collectors.toList());

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

    public double getPrecioTotalPaquete() {
        return precioTotalPaquete;
    }

    public boolean isPagada() {
        return pagada;
    }
}

