package bookReviewer.business.boundary.out.externalSystems;

import bookReviewer.business.useCase.query.getOffersOfBookUseCase.OfferOutput;

import java.util.ArrayList;

public interface ReceiveOffersOfYourFavoriteBookVendor {
    ArrayList<OfferOutput> receiveOffers(String isbn) throws Exception;
}
