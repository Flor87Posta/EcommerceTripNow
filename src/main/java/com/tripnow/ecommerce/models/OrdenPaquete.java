package com.tripnow.ecommerce.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/*
@Entity
public class OrdenPaquete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="orden_id")
    private Orden orden;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="paquete_id")
    private Paquete paquete;

    public OrdenPaquete(){};


    //Metodos accesores:


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
}*/
