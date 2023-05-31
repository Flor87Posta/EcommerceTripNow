package com.tripnow.ecommerce.services.implement;
import com.tripnow.ecommerce.models.Destino;
import com.tripnow.ecommerce.repositories.DestinoRepositorio;
import com.tripnow.ecommerce.services.DestinoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinoServicioImplementacion implements DestinoServicio {

    @Autowired
    DestinoRepositorio destinoRepositorio;
    @Override
    public Destino getDestino(long destinoId) {
        return destinoRepositorio.findById(destinoId).orElse(null);
    }
}
