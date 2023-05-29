package com.tripnow.ecommerce.services.implement;
import com.tripnow.ecommerce.Dto.OrdenDTO;
import com.tripnow.ecommerce.models.Orden;
import com.tripnow.ecommerce.services.OrdenServicio;
import java.util.List;

public class OrdenPaqueteServicioImplementacion implements OrdenServicio {
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

    }

    @Override
    public List<Orden> findByClient(Orden orden) {
        return null;
    }
}
