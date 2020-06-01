package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.useCase.query.getOffersOfBookUseCase.OfferOutput;

import java.util.List;

public interface GetOffersOfBookUseCase {
    List<OfferOutput> getOffers(Long bookId);
}
