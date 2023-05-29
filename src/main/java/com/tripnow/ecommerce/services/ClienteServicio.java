package com.tripnow.ecommerce.services;
import com.tripnow.ecommerce.Dto.ClienteDTO;
import com.tripnow.ecommerce.models.Cliente;
import java.util.List;


public interface ClienteServicio {
    List<ClienteDTO> getClientesDTO();
    ClienteDTO getClienteDTO(Long id);
    Cliente findByEmail(String email);

    Cliente  findById(Long id);
    void saveCliente(Cliente cliente);
}
