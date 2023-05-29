package com.tripnow.ecommerce.repositories;
import com.tripnow.ecommerce.models.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PaqueteRepositorio extends JpaRepository<Paquete, Long> {
}
