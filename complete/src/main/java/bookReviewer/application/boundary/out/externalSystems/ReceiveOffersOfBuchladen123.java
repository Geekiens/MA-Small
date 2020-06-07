package bookReviewer.application.boundary.out.externalSystems;

import bookReviewer.application.useCase.query.getOffersOfBookUseCase.OfferOutput;

import java.util.ArrayList;

public interface ReceiveOffersOfBuchladen123 {
    ArrayList<OfferOutput> receiveOffers(String isbn) throws Exception;
}
