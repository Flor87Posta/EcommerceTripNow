package com.tripnow.ecommerce.services.implement;
import com.tripnow.ecommerce.Dto.PaqueteDTO;
import com.tripnow.ecommerce.models.Paquete;
import com.tripnow.ecommerce.repositories.PaqueteRepositorio;
import com.tripnow.ecommerce.services.PaqueteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.util.stream.Collectors.toList;
@Service
public class PaqueteServicioImplementacion implements PaqueteServicio {

    @Autowired
    PaqueteRepositorio paqueteRepositorio;

    @Override
    public void savePaquete(Paquete paquete) {
            paqueteRepositorio.save(paquete);
    }
    @Override
    public List<PaqueteDTO> getPaquetesDTO() {
        return paqueteRepositorio.findAll().stream().map(PaqueteDTO::new).collect(toList());
    }

    @Override
    public void deletePaquete(Paquete paquete) {
        paqueteRepositorio.delete(paquete);
    }
}
