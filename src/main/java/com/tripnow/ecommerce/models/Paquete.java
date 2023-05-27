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
    private int cantidadStock;

    @OneToMany(mappedBy = "paquete", fetch = FetchType.EAGER)
    private Set<OrdenPaquete> ordenesPaquetes = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pasaje_id" )
    private Pasaje pasaje;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destino_id")
    private Destino destino;

    public Paquete(){}
    public Paquete(String nombrePaquete, int dias, double precioTotalUnitario, int cantidadStock){
        this.nombrePaquete = nombrePaquete;
        this.dias = dias;
        this.precioTotalUnitario= precioTotalUnitario;
        this.cantidadStock= cantidadStock;
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

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
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

    public Set<OrdenPaquete> getOrdenesPaquetes() {
        return ordenesPaquetes;
    }

    public void setOrdenesPaquetes(Set<OrdenPaquete> ordenesPaquetes) {
        this.ordenesPaquetes = ordenesPaquetes;
    }
}
