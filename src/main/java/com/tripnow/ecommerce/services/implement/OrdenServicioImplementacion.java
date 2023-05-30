package com.tripnow.ecommerce.services.implement;

import com.tripnow.ecommerce.Dto.ClienteDTO;
import com.tripnow.ecommerce.Dto.OrdenDTO;
import com.tripnow.ecommerce.models.Cliente;
import com.tripnow.ecommerce.models.Orden;
import com.tripnow.ecommerce.repositories.OrdenRepositorio;
import com.tripnow.ecommerce.services.OrdenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class OrdenServicioImplementacion implements OrdenServicio {

    @Autowired
    OrdenRepositorio ordenRepositorio;

    @Override
    public List<OrdenDTO> getOrdenesDTO() {
        return ordenRepositorio.findAll().stream().map(orden -> new OrdenDTO(orden)).collect(toList());
    }

    @Override
    public OrdenDTO getOrdenDTO(Long id) {
        return new OrdenDTO(this.findById(id));
    }

    @Override
    public Orden findById(Long id) {
        return ordenRepositorio.findById(id).orElse(null);
    }


    @Override
    public void saveOrden(Orden orden) {
        ordenRepositorio.save(orden);
    }

    @Override
    public List<Orden> findByClient(Cliente cliente) {
        return this.ordenRepositorio.findByClient(cliente);
    }
}
