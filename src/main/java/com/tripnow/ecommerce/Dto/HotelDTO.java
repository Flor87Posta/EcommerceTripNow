package com.tripnow.ecommerce.Dto;

import com.tripnow.ecommerce.models.Hotel;
import com.tripnow.ecommerce.models.HotelCategoria;

public class HotelDTO {
    private long id;
    private String nombre;
    private HotelCategoria categoria;
    private boolean allInclusive;
    private boolean desayuno;
    private boolean mediaPension;
    private double precioHotel;
    private int cantidadStock;

    public HotelDTO(){
    }

    public HotelDTO(Hotel hotel){
        this.id = hotel.getId();
        this.nombre = hotel.getNombre();
        this.categoria = hotel.getCategoria();
        this.allInclusive = hotel.isAllInclusive();
        this.desayuno = hotel.isDesayuno();
        this.mediaPension = hotel.isMediaPension();
        this.precioHotel = hotel.getPrecioHotel();
        this.cantidadStock = hotel.getCantidadStock();
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

    public HotelCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(HotelCategoria categoria) {
        this.categoria = categoria;
    }

    public boolean isAllInclusive() {
        return allInclusive;
    }

    public void setAllInclusive(boolean allInclusive) {
        this.allInclusive = allInclusive;
    }

    public boolean isDesayuno() {
        return desayuno;
    }

    public void setDesayuno(boolean desayuno) {
        this.desayuno = desayuno;
    }

    public boolean isMediaPension() {
        return mediaPension;
    }

    public void setMediaPension(boolean mediaPension) {
        this.mediaPension = mediaPension;
    }

    public double getPrecioHotel() {
        return precioHotel;
    }

    public void setPrecioHotel(double precioHotel) {
        this.precioHotel = precioHotel;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }
}
