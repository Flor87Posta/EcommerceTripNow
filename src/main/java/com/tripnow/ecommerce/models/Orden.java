package com.tripnow.ecommerce.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long id;
    private LocalDateTime fechaCreacion;
    private boolean activa;
    private int cantidadPasajeros;
    private double precioTotalPaquetes;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Cliente cliente;

    @OneToMany(mappedBy="orden", fetch= FetchType.EAGER) //asociado a orden definido en la clase OrdenPaquete, fijarse q
    // este el mappedBy para que no genere una tabla intermedia en blanco
    private Set<OrdenPaquete> ordenesPaquetes = new HashSet<>(); // set para evitar datos duplicados
    //ordenesPaquetes como nueva propiedad de la tabla Orden

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pago_id")
    private Pago pago;


    public Orden(){};

    public Orden(LocalDateTime fechaCreacion, boolean activa, int cantidadPasajeros, double precioTotalPaquetes ){
    this.fechaCreacion = fechaCreacion;
    this.activa=activa;
    this.cantidadPasajeros =cantidadPasajeros;
    this.precioTotalPaquetes=precioTotalPaquetes;
    };

    //Metodos accesores:


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public double getPrecioTotalPaquetes() {
        return precioTotalPaquetes;
    }

    public void setPrecioTotalPaquetes(double precioTotalPaquetes) {
        this.precioTotalPaquetes = precioTotalPaquetes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<OrdenPaquete> getOrdenesPaquetes() {
        return ordenesPaquetes;
    }

    public void setOrdenesPaquetes(Set<OrdenPaquete> ordenesPaquetes) {
        this.ordenesPaquetes = ordenesPaquetes;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
