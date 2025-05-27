package tech.backend.seuimovel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.backend.seuimovel.entities.Listing;

public interface ListingRepository extends JpaRepository<Listing, Long> {
}
