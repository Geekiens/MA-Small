package bookReviewer.application.boundary.in.useCase.query;

import bookReviewer.application.useCase.query.getOffersOfBookUseCase.OfferOutput;

import java.util.List;

public interface GetOffersOfBookUseCase {
    List<OfferOutput> getOffers(Long bookId);
}
