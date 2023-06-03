package com.tripnow.ecommerce.services;

import com.tripnow.ecommerce.Dto.ClienteDTO;
import com.tripnow.ecommerce.Dto.DestinoDTO;
import com.tripnow.ecommerce.models.Destino;
import com.tripnow.ecommerce.models.Paquete;

import java.util.List;

public interface DestinoServicio {
    Destino getDestino(long destinoId);
    void saveDestino(Destino destino);
    List<DestinoDTO> getDestinosDTO();


}
