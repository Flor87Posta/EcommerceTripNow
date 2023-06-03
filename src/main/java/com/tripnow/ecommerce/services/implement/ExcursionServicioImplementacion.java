package com.tripnow.ecommerce.services.implement;
import com.tripnow.ecommerce.Dto.ExcursionDTO;
import com.tripnow.ecommerce.Dto.PasajeDTO;
import com.tripnow.ecommerce.models.Excursion;
import com.tripnow.ecommerce.repositories.ExcursionRepositorio;
import com.tripnow.ecommerce.services.ExcursionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ExcursionServicioImplementacion implements ExcursionServicio {
    @Autowired
    ExcursionRepositorio excursionRepositorio;
    @Override
    public void saveExcursion(Excursion excursion) {
       excursionRepositorio.save(excursion);
    }

    @Override
    public List<ExcursionDTO> getExcursionesDTO() {
        return excursionRepositorio.findAll().stream().map(excursion -> new ExcursionDTO(excursion)).collect(toList());

    }
}
