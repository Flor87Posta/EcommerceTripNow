package com.tripnow.ecommerce.services.implement;

import com.tripnow.ecommerce.Dto.OrdenDTO;
import com.tripnow.ecommerce.models.Orden;
import com.tripnow.ecommerce.repositories.OrdenRepositorio;
import com.tripnow.ecommerce.services.ClienteServicio;
import com.tripnow.ecommerce.services.OrdenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenServicioImplementacion implements OrdenServicio {

    @Autowired
    OrdenRepositorio ordenRepositorio;

    @Override
    public List<OrdenDTO> getOrdenesDTO() {
        return null;
    }

    @Override
    public OrdenDTO getOrdenDTO(Long id) {
        return null;
    }

    @Override
    public Orden findById(Long id) {
        return null;
    }

    @Override
    public boolean existsByNumber(String number) {
        return false;
    }

    @Override
    public void saveOrden(Orden orden) {
        ordenRepositorio.save(orden);
    }

    @Override
    public List<Orden> findByClient(Orden orden) {
        return null;
    }
}
