package com.tripnow.ecommerce.repositories;
import com.tripnow.ecommerce.models.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ExcursionRepositorio extends JpaRepository<Excursion, Long> {
}
