package com.tripnow.ecommerce.services;
import com.tripnow.ecommerce.Dto.PasajeDTO;
import com.tripnow.ecommerce.models.Pasaje;

import java.util.List;

public interface PasajeServicio {

    Pasaje getPasaje(long pasajeId);

    void savePasaje(Pasaje pasaje);

    List<PasajeDTO> getPasajesDTO();
}
