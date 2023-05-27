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

    @OneToMany(mappedBy = "excursion", fetch = FetchType.EAGER)
    private Set<DestinoExcursion> destinosExcursiones = new HashSet<>();

    public Excursion() {
    }
    public Excursion(String nombre, String actividad, double precioExcursion) {
        this.nombre = nombre;
        this.actividad = actividad;
        this.precioExcursion = precioExcursion;
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

    public Set<DestinoExcursion> getDestinosExcursiones() {
        return destinosExcursiones;
    }

    public void setDestinosExcursiones(Set<DestinoExcursion> destinosExcursiones) {
        this.destinosExcursiones = destinosExcursiones;
    }
}