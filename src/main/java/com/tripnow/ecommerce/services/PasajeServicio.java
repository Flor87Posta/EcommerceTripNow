package com.tripnow.ecommerce.services;
import com.tripnow.ecommerce.models.Pasaje;

public interface PasajeServicio {

    Pasaje getPasaje(long pasajeId);

    void savePasaje(Pasaje pasaje);
}
