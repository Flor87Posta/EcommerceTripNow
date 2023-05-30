package com.tripnow.ecommerce.services;

import com.tripnow.ecommerce.Dto.OrdenDTO;
import com.tripnow.ecommerce.models.Cliente;
import com.tripnow.ecommerce.models.Orden;
import com.tripnow.ecommerce.models.Paquete;

import java.util.List;

public interface OrdenServicio {
    List<OrdenDTO> getOrdenesDTO();
    OrdenDTO getOrdenDTO(Long id);
    Orden findById(Long id);

    void saveOrden(Orden orden);

    void deleteOrden(Orden orden);

//    List<Orden> findByClient(Cliente cliente);
}
