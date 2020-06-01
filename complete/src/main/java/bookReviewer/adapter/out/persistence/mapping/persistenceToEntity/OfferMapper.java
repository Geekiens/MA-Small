package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.adapter.out.persistence.model.Offer;

import java.util.ArrayList;
import java.util.List;

public final class OfferMapper {
    public static bookReviewer.entity.offerHistory.Offer map(Offer offerPersistence) {
        bookReviewer.entity.offerHistory.Offer offer = new bookReviewer.entity.offerHistory.Offer();
        offer.setPrice(offerPersistence.getPrice());
        offer.setRequestDate(offerPersistence.getRequestDate());
        return offer;
    }
    public static ArrayList<bookReviewer.entity.offerHistory.Offer> mapList(List<Offer> offerList){
        ArrayList<bookReviewer.entity.offerHistory.Offer> offers = new ArrayList<>();
        for(Offer offer : offerList){
            offers.add(map(offer));
        }
        return  offers;
    }
}
