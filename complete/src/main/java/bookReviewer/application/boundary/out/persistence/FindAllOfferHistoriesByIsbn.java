package bookReviewer.application.boundary.out.persistence;

import bookReviewer.entity.offerHistory.OfferHistroy;

import java.util.List;

public interface FindAllOfferHistoriesByIsbn {
    List<OfferHistroy> findAllOffersByIsbn(String isbn);
}
