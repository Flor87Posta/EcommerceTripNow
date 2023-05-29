package com.tripnow.ecommerce.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long id;
    private String nombre;
    private String apellido;
    private String pasaporte;
    private String direccion;
    private String telefono;
    private Date fechaNac;
    private String email;
    private String contrasena;


    @OneToMany(mappedBy="cliente", fetch= FetchType.EAGER) //asociado a cliente definido en la clase Orden, fijarse q
    // este el mappedBy para que no genere una tabla intermedia en blanco
    private Set<Orden> ordenes = new HashSet<>(); // set para evitar datos duplicados
    //ordenes como nueva propiedad de la tabla Cliente

    public Cliente(){};

    public Cliente(String nombre, String apellido, String pasaporte, String direccion, String telefono, String email, String contrasena, Date fechaNac) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pasaporte = pasaporte;
        this.direccion =direccion;
        this.telefono =telefono;
        this.email = email;
        this.contrasena = contrasena;
        this.fechaNac = fechaNac;

    }

    //Métodos accesores:

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public Set<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(Set<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    //Metodos creados:
    // Método para añadir nueva orden:
    public void añadirOrden(Orden orden){
        orden.setCliente(this);
        ordenes.add(orden); //ordenes es Set
    }

}
