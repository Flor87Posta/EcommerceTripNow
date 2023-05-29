package com.tripnow.ecommerce.services.implement;

import com.tripnow.ecommerce.Dto.ClienteDTO;
import com.tripnow.ecommerce.models.Cliente;
import com.tripnow.ecommerce.repositories.ClienteRepositorio;
import com.tripnow.ecommerce.services.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Service
public class ClienteServicioImplementacion implements ClienteServicio {

    @Autowired
    ClienteRepositorio clienteRepositorio;

    @Override
    public List<ClienteDTO> getClientesDTO() {
        return clienteRepositorio.findAll().stream().map(cliente -> new ClienteDTO (cliente)).collect(toList());
    }

    @Override
    public ClienteDTO getClienteDTO(Long id) {
        return new ClienteDTO(this.findById(id));
    }

    @Override
    public Cliente findByEmail(String email) {
        return clienteRepositorio.findByEmail(email);
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

    @Override
    public void saveCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }
}
