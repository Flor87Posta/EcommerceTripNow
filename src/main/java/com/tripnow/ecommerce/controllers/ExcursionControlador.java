package com.tripnow.ecommerce.controllers;

import com.tripnow.ecommerce.Dto.ExcursionDTO;

import com.tripnow.ecommerce.services.ExcursionServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ExcursionControlador {
    @Autowired
    ExcursionServicio excursionServicio;

    @GetMapping("/api/excursiones")
    public List<ExcursionDTO> getExcursionesDTO() {
        return excursionServicio.getExcursionesDTO();    }
}
