package com.tripnow.ecommerce.repositories;
import com.tripnow.ecommerce.models.Cliente;
import com.tripnow.ecommerce.models.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrdenRepositorio extends JpaRepository<Orden, Long> {

/*    @Query("SELECT a FROM Account a WHERE a.cliente = :cliente AND a.activa = true" )
    List<Orden> findByClient(@Param("cliente") Cliente cliente);*/
    // método que arroja la lista de ordenes del cliente y que estan activas.

}
