package com.tripnow.ecommerce.Dto;

import com.tripnow.ecommerce.models.Hotel;
import com.tripnow.ecommerce.models.Paquete;

import java.util.List;
import java.util.stream.Collectors;

public class PaqueteDTO {

    private long id;
    private String nombrePaquete;
    private int dias;
    private double precioTotalUnitario;
    private int stock;
    private String imagen1;

    private List<HotelDTO> hotelesDTO;

    public PaqueteDTO() {}

    public PaqueteDTO(Paquete paquete) {
        this.id = paquete.getId();
        this.nombrePaquete = paquete.getNombrePaquete();
        this.dias = paquete.getDias();
        this.precioTotalUnitario = paquete.getPrecioTotalUnitario();
        this.stock = paquete.getStock();
        this.imagen1 = paquete.getImagen1();
        this.hotelesDTO = paquete.getDestino().getHoteles()
                .stream()
                .map(hotel -> new HotelDTO(hotel))
                .collect(Collectors.toList());
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

    public List<HotelDTO> getHotelesDTO() {
        return hotelesDTO;
    }

    public void setHotelesDTO(List<HotelDTO> hotelesDTO) {
        this.hotelesDTO = hotelesDTO;
    }
}
