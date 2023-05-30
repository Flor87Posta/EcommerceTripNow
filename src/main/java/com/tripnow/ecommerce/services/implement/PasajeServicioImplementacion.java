package com.tripnow.ecommerce.services.implement;

import com.tripnow.ecommerce.models.Pasaje;
import com.tripnow.ecommerce.repositories.PasajeRepositorio;
import com.tripnow.ecommerce.services.PasajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasajeServicioImplementacion implements PasajeServicio {
    @Autowired
    PasajeRepositorio pasajeRepositorio;
    @Override
    public Pasaje getPasaje(long pasajeId) {
        return pasajeRepositorio.findById(pasajeId).orElse(null);
    }
}
