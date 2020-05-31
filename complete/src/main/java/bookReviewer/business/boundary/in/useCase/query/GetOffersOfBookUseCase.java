package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.useCase.query.getOffersOfBookUseCase.Offer;

import java.util.List;

public interface GetOffersOfBookUseCase {
    List<Offer> getOffers(Long bookId);
}
