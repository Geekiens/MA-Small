package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.model.Offer;

import java.util.List;

public interface GetOffersOfBookQuery {
    List<Offer> getOffers(Long bookId);
}
