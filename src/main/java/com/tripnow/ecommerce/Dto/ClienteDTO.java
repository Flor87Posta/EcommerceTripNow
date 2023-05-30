package com.tripnow.ecommerce.Dto;

import com.tripnow.ecommerce.models.Cliente;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTO {
    private long id;
    private String nombre;
    private String apellido;
    private String pasaporte;
    private String direccion;
    private String telefono;
    private Date fechaNac;
    private String email;
    private String contrasena;
    private List<OrdenDTO> ordenesDTO;

    public ClienteDTO() {
    }
    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.pasaporte = cliente.getPasaporte();
        this.direccion = cliente.getDireccion();
        this.telefono = cliente.getTelefono();
        this.fechaNac = cliente.getFechaNac();
        this.email = cliente.getEmail();
        this.contrasena = cliente.getContrasena();
        this.ordenesDTO = cliente.getOrdenes()
                .stream()
                .map(orden -> new OrdenDTO(orden))
                .collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getPasaporte() {
        return pasaporte;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public Date getFechaNac() {
        return fechaNac;
    }
    public String getEmail() {
        return email;
    }
    public String getContrasena() {
        return contrasena;
    }

    public List<OrdenDTO> getOrdenesDTO() {
        return ordenesDTO;
    }
}
