package com.tripnow.ecommerce.controllers;

import com.tripnow.ecommerce.Dto.DestinoDTO;
import com.tripnow.ecommerce.Dto.PasajeDTO;
import com.tripnow.ecommerce.repositories.PasajeRepositorio;
import com.tripnow.ecommerce.services.DestinoServicio;
import com.tripnow.ecommerce.services.PasajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class PasajeControlador {
    @Autowired
    PasajeServicio pasajeServicio;

    @GetMapping("/api/pasajes")
    public List<PasajeDTO> getPasajesDTO() {
        return pasajeServicio.getPasajesDTO();
    }
}
