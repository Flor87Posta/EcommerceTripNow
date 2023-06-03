package com.tripnow.ecommerce.services;
import com.tripnow.ecommerce.Dto.ExcursionDTO;
import com.tripnow.ecommerce.Dto.PasajeDTO;
import com.tripnow.ecommerce.models.Excursion;

import java.util.List;


public interface ExcursionServicio {

    void saveExcursion(Excursion excursion);
    List<ExcursionDTO> getExcursionesDTO();
}
