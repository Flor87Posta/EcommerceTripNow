package com.tripnow.ecommerce.repositories;
import com.tripnow.ecommerce.models.Pasaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PasajeRepositorio extends JpaRepository<Pasaje, Long> {
}
