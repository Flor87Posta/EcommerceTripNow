package com.tripnow.ecommerce.repositories;
import com.tripnow.ecommerce.models.OrdenPaquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrdenPaqueteRepositorio extends JpaRepository<OrdenPaquete, Long> {
}
