package com.tripnow.ecommerce.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String nombrePaquete;
    private int dias;
    private double precioTotalUnitario;
    private int stock;

    private String imagen1;



    @ManyToMany(mappedBy = "paquetes", fetch = FetchType.EAGER)
    private Set<Orden> ordenes = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pasaje_id" )
    private Pasaje pasaje;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destino_id")
    private Destino destino;

    public Paquete(){}
    public Paquete(String nombrePaquete, int dias, double precioTotalUnitario, int stock, String imagen1){
        this.nombrePaquete = nombrePaquete;
        this.dias = dias;
        this.precioTotalUnitario= precioTotalUnitario;
        this.stock = stock;
        this.imagen1=imagen1;


    }

    public long getId() {
        return id;
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


    public Pasaje getPasaje() {
        return pasaje;
    }

    public void setPasaje(Pasaje pasaje) {
        this.pasaje = pasaje;
    }

     public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Set<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(Set<Orden> ordenes) {
        this.ordenes = ordenes;
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


    //Metodos creados:



}
