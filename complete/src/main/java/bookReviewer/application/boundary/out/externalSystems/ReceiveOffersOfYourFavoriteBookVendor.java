package bookReviewer.application.boundary.out.externalSystems;

import bookReviewer.application.useCase.query.getOffersOfBookUseCase.OfferOutput;

import java.util.ArrayList;

public interface ReceiveOffersOfYourFavoriteBookVendor {
    ArrayList<OfferOutput> receiveOffers(String isbn) throws Exception;
}
