package com.tripnow.ecommerce.repositories;
import com.tripnow.ecommerce.models.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DestinoRepositorio extends JpaRepository <Destino, Long> {
}
