package com.tripnow.ecommerce.services;
import com.tripnow.ecommerce.Dto.PaqueteDTO;
import com.tripnow.ecommerce.models.Paquete;
import java.util.List;

public interface PaqueteServicio {
    void savePaquete(Paquete paquete);
    List<PaqueteDTO> getPaquetesDTO();

    void deletePaquete(Paquete paquete);
}
