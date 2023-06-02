package com.tripnow.ecommerce.Dto;

import com.tripnow.ecommerce.models.Destino;

import java.util.List;
import java.util.stream.Collectors;

public class DestinoDTO {
    private long id;
    private String nombre;
    private List<Integer> cantidadDias;
    private double precioHotelExcursion;
    private List<ExcursionDTO> excursionesDTO;
    private List<HotelDTO> hotelesDTO;

    public DestinoDTO() {
    }

    public DestinoDTO(Destino destino){
        this.id = destino.getId();
        this.nombre = destino.getNombre();
        this.cantidadDias = destino.getCantidadDias();
        this.precioHotelExcursion = destino.getPrecioHotelExcursion();
        this.excursionesDTO = destino.getExcursiones().stream().map(excursion -> new ExcursionDTO(excursion)).collect(Collectors.toList());
        this.hotelesDTO = destino.getHoteles().stream().map(hotel -> new HotelDTO(hotel)).collect(Collectors.toList());
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

    public List<Integer> getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(List<Integer> cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public double getPrecioHotelExcursion() {
        return precioHotelExcursion;
    }

    public void setPrecioHotelExcursion(double precioHotelExcursion) {
        this.precioHotelExcursion = precioHotelExcursion;
    }

    public List<ExcursionDTO> getExcursionesDTO() {
        return excursionesDTO;
    }

    public void setExcursionesDTO(List<ExcursionDTO> excursionesDTO) {
        this.excursionesDTO = excursionesDTO;
    }

    public List<HotelDTO> getHotelesDTO() {
        return hotelesDTO;
    }

    public void setHotelesDTO(List<HotelDTO> hotelesDTO) {
        this.hotelesDTO = hotelesDTO;
    }
}
