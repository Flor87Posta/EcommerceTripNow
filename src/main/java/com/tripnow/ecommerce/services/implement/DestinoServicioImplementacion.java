package com.tripnow.ecommerce.services.implement;
import com.tripnow.ecommerce.Dto.ClienteDTO;
import com.tripnow.ecommerce.Dto.DestinoDTO;
import com.tripnow.ecommerce.models.Destino;
import com.tripnow.ecommerce.repositories.DestinoRepositorio;
import com.tripnow.ecommerce.services.DestinoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DestinoServicioImplementacion implements DestinoServicio {

    @Autowired
    DestinoRepositorio destinoRepositorio;
    @Override
    public Destino getDestino(long destinoId) {
        return destinoRepositorio.findById(destinoId).orElse(null);
    }

    @Override
    public void saveDestino(Destino destino) {
        destinoRepositorio.save(destino);
    }

    @Override
    public List<DestinoDTO> getDestinosDTO() {
        return destinoRepositorio.findAll().stream().map(destino -> new DestinoDTO(destino)).collect(toList());
    }
}
