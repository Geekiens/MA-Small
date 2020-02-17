package bookReviewer.persistence.repository;

import bookReviewer.persistence.model.OfferPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferPersistence, Long> {
}
