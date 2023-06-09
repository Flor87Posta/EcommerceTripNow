package com.tripnow.ecommerce.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String nombre;
    private String actividad;
    private double precioExcursion;
    private int cantidadStock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="destino_id")
    private Destino destino;


    public Excursion() {
    }
    public Excursion(String nombre, String actividad, double precioExcursion, int cantidadStock) {
        this.nombre = nombre;
        this.actividad = actividad;
        this.precioExcursion = precioExcursion;
        this.cantidadStock=cantidadStock;
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

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

}