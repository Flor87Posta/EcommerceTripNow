package com.tripnow.ecommerce.repositories;
import com.tripnow.ecommerce.models.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrdenRepositorio extends JpaRepository<Orden, Long> {
}
