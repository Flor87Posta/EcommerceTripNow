package com.tripnow.ecommerce.controllers;
import com.tripnow.ecommerce.Dto.ClienteDTO;
import com.tripnow.ecommerce.models.Cliente;
import com.tripnow.ecommerce.services.ClienteServicio;
import com.tripnow.ecommerce.services.OrdenServicio;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;


@RestController
public class ClienteControlador {

    @Autowired

    private PasswordEncoder passwordEncoder;
    @Autowired
    ClienteServicio clienteServicio;

    @Autowired
    OrdenServicio ordenServicio;

    @GetMapping("/api/clientes")
    public List<ClienteDTO> getClientesDTO() {
        return clienteServicio.getClientesDTO();
    }


    @GetMapping("/api/clientes/{id}")
    public ClienteDTO getClienteDTO(@PathVariable Long id) {
        return  clienteServicio.getClienteDTO(id);
    }

    @PostMapping("/api/clientes")
    public ResponseEntity<Object> registro(

            @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String pasaporte, @RequestParam String direccion,
            @RequestParam String telefono,
            @RequestParam String email, @RequestParam String contrasena, @RequestParam Date fechaNac) { //son todos los parámetros que envía el usuario al registrarse

        // Registra al usuario y genera un token de verificación

    /*    String token = emailService.register(user);

    // Envía un correo electrónico de confirmación al usuario

        emailService.sendConfirmationEmail(email, token);*/
        if (nombre.isEmpty() ) {
            return new ResponseEntity<>("Por favor escribe tu nombre", HttpStatus.FORBIDDEN); //código de estado HTTP 403 prohibido
        }

        if (apellido.isEmpty()) {
            return new ResponseEntity<>("Por favor escribe tu apellido", HttpStatus.FORBIDDEN); //código de estado HTTP 403 prohibido
        }

        if (pasaporte.isEmpty()) {
            return new ResponseEntity<>("Por favor escribe tu numero de Pasaporte", HttpStatus.FORBIDDEN); //código de estado HTTP 403 prohibido
        }

        if (direccion.isEmpty()) {
            return new ResponseEntity<>("Por favor escribe tu dirección", HttpStatus.FORBIDDEN); //código de estado HTTP 403 prohibido
        }

        if (telefono.isEmpty()) {
            return new ResponseEntity<>("Por favor escribe tu numero de teléfono", HttpStatus.FORBIDDEN); //código de estado HTTP 403 prohibido
        }

        if (fechaNac==null) {
            return new ResponseEntity<>("Por favor escribe tu fecha de nacimiento", HttpStatus.FORBIDDEN); //código de estado HTTP 403 prohibido
        }

        if (email.isEmpty()) {
            return new ResponseEntity<>("Por favor escribe tu email", HttpStatus.FORBIDDEN); //código de estado HTTP 403 prohibido
        }

        if (contrasena.isEmpty()) {
            return new ResponseEntity<>("Por favor escribe tu contraseña", HttpStatus.FORBIDDEN); //código de estado HTTP 403 prohibido
        }

        if (clienteServicio.findByEmail(email) != null) {
            return new ResponseEntity<>("Usuario ya existente", HttpStatus.FORBIDDEN); //código de estado HTTP 403 prohibido

        }

        Cliente nuevoCliente = new Cliente(nombre, apellido, pasaporte, direccion, telefono, email, passwordEncoder.encode(contrasena), fechaNac);
        clienteServicio.saveCliente(nuevoCliente);
        return new ResponseEntity<>(HttpStatus.CREATED); //código de estado HTTP 201 creado

    }


    //    @RequestMapping("/clientes/current")
    // este servlet es para crear un nuevo servicio que retorne toda la información del usuario autenticado,
    // antes usabamos /clients/1 que es Melba

    @GetMapping("/api/clientes/current")
    public ClienteDTO getClienteCurrent(Authentication authentication) {
        Cliente cliente = clienteServicio.findByEmail(authentication.getName());//Si hay un usuario conectado, authentication.getName() devolverá el nombre que la clase WebAuthentication puso en el objeto User.
        return new ClienteDTO(cliente); //al client que cree que guarda el usuario autenticado lo transformo en clientDTO que tiene todas las propiedades
    }
}
