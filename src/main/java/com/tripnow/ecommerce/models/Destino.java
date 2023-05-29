package com.tripnow.ecommerce.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String nombre;
    @ElementCollection
    @Column(name="Dias")
    List<Integer> cantidadDias;
    private double precioHotelExcursion;
    @OneToMany(mappedBy = "destino", fetch = FetchType.EAGER)
    Set<DestinoExcursion> destinosExcursiones = new HashSet<>();
    @OneToMany(mappedBy = "destino", fetch = FetchType.EAGER)
    Set<Hotel> hoteles = new HashSet<>();
    @OneToMany(mappedBy = "destino", fetch = FetchType.EAGER)
    Set<Paquete> paquetes = new HashSet<>();

    public Destino(){};
    public Destino(String nombre, List<Integer> cantidadDias, double precioHotelExcursion) {
        this.nombre = nombre;
        this.cantidadDias = cantidadDias;
        this.precioHotelExcursion = precioHotelExcursion;
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

    public Set<DestinoExcursion> getDestinosExcursiones() {
        return destinosExcursiones;
    }

    public void setDestinosExcursiones(Set<DestinoExcursion> destinosExcursiones) {
        this.destinosExcursiones = destinosExcursiones;
    }

    public Set<Hotel> getHoteles() {
        return hoteles;
    }

    public void setHoteles(Set<Hotel> hoteles) {
        this.hoteles = hoteles;
    }

    public Set<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(Set<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    // Método para añadir un destinoExcursion:
    public void añadirDestinoExcursion(DestinoExcursion destinoExcursion){
        destinoExcursion.setDestino(this);
        destinosExcursiones.add(destinoExcursion); // destinosExcursiones es el Set
    }

    // Método para añadir un hotel:

    public void añadirHotel(Hotel hotel){
        hotel.setDestino(this);
        hoteles.add(hotel); // destinosExcursiones es el Set
    }

    // Método para añadir un hotel:

    public void añadirPaquete(Paquete paquete){
        paquete.setDestino(this);
        paquetes.add(paquete); // destinosExcursiones es el Set
    }

}
