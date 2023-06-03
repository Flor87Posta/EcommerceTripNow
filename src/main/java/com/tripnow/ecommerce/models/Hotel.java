package com.tripnow.ecommerce.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String nombre;
    private HotelCategoria categoria;
    private boolean allInclusive;
    private boolean desayuno;
    private boolean mediaPension;
    private double precioHotel;
    private int cantidadStock;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destino_id")
    private Destino destino;


    public Hotel(){ }
    public Hotel(String nombre, HotelCategoria categoria, boolean allInclusive, boolean desayuno, boolean mediaPension, double precioHotel, int cantidadStock){
        this.nombre=nombre;
        this.categoria=categoria;
        this.allInclusive= allInclusive;
        this.desayuno=desayuno;
        this.mediaPension=mediaPension;
        this.precioHotel=precioHotel;
        this.cantidadStock=cantidadStock;

    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HotelCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(HotelCategoria categoria) {
        this.categoria = categoria;
    }

    public boolean isAllInclusive() {
        return allInclusive;
    }

    public void setAllInclusive(boolean allInclusive) {
        this.allInclusive = allInclusive;
    }

    public boolean isDesayuno() {
        return desayuno;
    }

    public void setDesayuno(boolean desayuno) {
        this.desayuno = desayuno;
    }

    public boolean isMediaPension() {
        return mediaPension;
    }

    public void setMediaPension(boolean mediaPension) {
        this.mediaPension = mediaPension;
    }

    public double getPrecioHotel() {
        return precioHotel;
    }

    public void setPrecioHotel(double precioHotel) {
        this.precioHotel = precioHotel;
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
