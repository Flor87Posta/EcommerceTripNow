package com.tripnow.ecommerce.controllers;

import com.tripnow.ecommerce.Dto.ClienteDTO;
import com.tripnow.ecommerce.Dto.DestinoDTO;
import com.tripnow.ecommerce.services.DestinoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DestinoControlador {
    @Autowired
    DestinoServicio destinoServicio;

    @GetMapping("/api/destinos")
    public List<DestinoDTO> getDestinosDTO() {
        return destinoServicio.getDestinosDTO();
    }
}
