package com.tripnow.ecommerce.services.implement;

import com.tripnow.ecommerce.Dto.DestinoDTO;
import com.tripnow.ecommerce.Dto.PasajeDTO;
import com.tripnow.ecommerce.models.Pasaje;
import com.tripnow.ecommerce.repositories.PasajeRepositorio;
import com.tripnow.ecommerce.services.PasajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PasajeServicioImplementacion implements PasajeServicio {
    @Autowired
    PasajeRepositorio pasajeRepositorio;
    @Override
    public Pasaje getPasaje(long pasajeId) {
        return pasajeRepositorio.findById(pasajeId).orElse(null);
    }

    @Override
    public void savePasaje(Pasaje pasaje) {
        pasajeRepositorio.save(pasaje);
    }

    @Override
    public List<PasajeDTO> getPasajesDTO() {
        return pasajeRepositorio.findAll().stream().map(pasaje -> new PasajeDTO(pasaje)).collect(toList());
    }
}
