package com.tripnow.ecommerce.repositories;

import com.tripnow.ecommerce.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HotelRepositorio extends JpaRepository<Hotel, Long> {
}
