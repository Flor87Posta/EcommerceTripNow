package com.tripnow.ecommerce.Dto;
import com.tripnow.ecommerce.models.Pasaje;
import com.tripnow.ecommerce.models.TipoPasaje;
import java.util.List;
import java.util.stream.Collectors;

public class PasajeDTO {

    private long id;
    private TipoPasaje tipo;
    private String compania;
    private double precioPasaje;
    private int cantidadStock;
    private List<PaqueteDTO> paquetesDTO;

    public PasajeDTO() {
    }
    public PasajeDTO(Pasaje pasaje){
        this.id = pasaje.getId();
        this.tipo = pasaje.getTipo();
        this.compania = pasaje.getCompania();
        this.precioPasaje = pasaje.getPrecioPasaje();
        this.cantidadStock = pasaje.getCantidadStock();
        this.paquetesDTO = pasaje.getPaquetes()
                .stream()
                .map(paquete -> new PaqueteDTO(paquete))
                .collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TipoPasaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoPasaje tipo) {
        this.tipo = tipo;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public double getPrecioPasaje() {
        return precioPasaje;
    }

    public void setPrecioPasaje(double precioPasaje) {
        this.precioPasaje = precioPasaje;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public List<PaqueteDTO> getPaquetesDTO() {
        return paquetesDTO;
    }

    public void setPaquetesDTO(List<PaqueteDTO> paquetesDTO) {
        this.paquetesDTO = paquetesDTO;
    }
}
