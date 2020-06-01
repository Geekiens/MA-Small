package bookReviewer.business.boundary.out.externalSystems;

import bookReviewer.business.useCase.query.getOffersOfBookUseCase.OfferOutput;

import java.util.ArrayList;

public interface ReceiveOffersOfBuchladen123 {
    ArrayList<OfferOutput> receiveOffers();
}
