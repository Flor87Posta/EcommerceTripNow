package com.tripnow.ecommerce.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Pasaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private TipoPasaje tipo;
    private String compania;

    private LocalDate fechaSalida;
    private LocalDate fechaRegreso;
    private double precioPasaje;

    private int cantidadStock;


    @OneToMany(mappedBy = "pasaje", fetch = FetchType.EAGER)
    private Set<Paquete> paquetes = new HashSet<>();


    public Pasaje(){ }
    public Pasaje(TipoPasaje tipo, String compania, LocalDate fechaSalida, LocalDate fechaRegreso, double precioPasaje, int cantidadStock){
        this.tipo= tipo;
        this.compania=compania;
        this.fechaSalida= fechaSalida;
        this.fechaRegreso=fechaRegreso;
        this.precioPasaje = precioPasaje;
        this.cantidadStock = cantidadStock;
    }


    public long getId() {
        return id;
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

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(LocalDate fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
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

    public Set<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(Set<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    // Método para añadir pasaje al paquete:
    public void añadirPasaje(Paquete paquete){
        paquete.setPasaje(this);
        paquetes.add(paquete); //paquetes es el Set
    }
}
