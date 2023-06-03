package com.tripnow.ecommerce.services.implement;

import com.tripnow.ecommerce.models.Hotel;
import com.tripnow.ecommerce.repositories.HotelRepositorio;
import com.tripnow.ecommerce.services.HotelServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServicioImplementacion implements HotelServicio {
    @Autowired
    HotelRepositorio hotelRepositorio;
    @Override
    public void saveHotel(Hotel hotel) {
      hotelRepositorio.save(hotel);
    }
}
