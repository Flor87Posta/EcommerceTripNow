package com.tripnow.ecommerce.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
public class DestinoExcursion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String nombreExcursion;
    private String actividadExcursion;
    private double precioExcursion;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="excursion_id")
    private Excursion excursion;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="destino_id")
    private Destino destino;

    public DestinoExcursion(){};

    public DestinoExcursion(String nombreExcursion, String actividadExcursion, double precioExcursion, Excursion excursion, Destino destino) {
        this.nombreExcursion = nombreExcursion;
        this.actividadExcursion = actividadExcursion;
        this.precioExcursion = precioExcursion;
        this.excursion = excursion;
        this.destino = destino;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreExcursion() {
        return nombreExcursion;
    }

    public void setNombreExcursion(String nombreExcursion) {
        this.nombreExcursion = nombreExcursion;
    }

    public String getActividadExcursion() {
        return actividadExcursion;
    }

    public void setActividadExcursion(String actividadExcursion) {
        this.actividadExcursion = actividadExcursion;
    }

    public double getPrecioExcursion() {
        return precioExcursion;
    }

    public void setPrecioExcursion(double precioExcursion) {
        this.precioExcursion = precioExcursion;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }
}
