package bookReviewer.periphery.persistence.repository;

import bookReviewer.adapter.out.persistence.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepositoryJpa extends JpaRepository<Offer, Long> {
}
