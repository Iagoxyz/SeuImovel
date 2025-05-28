package tech.backend.seuimovel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.backend.seuimovel.entities.Listing;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByUsuarioId(Long usuarioId);
}
