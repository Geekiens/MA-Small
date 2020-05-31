package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.offerHistory.Offer;
import bookReviewer.persistence.model.OfferPersistence;

import java.util.ArrayList;
import java.util.List;

public final class OfferMapper {
    public static Offer map(OfferPersistence offerPersistence) {
        Offer offer = new Offer();
        offer.setPrice(offerPersistence.getPrice());
        offer.setRequestDate(offerPersistence.getRequestDate());
        return offer;
    }
    public static ArrayList<Offer> mapList(List<OfferPersistence> offerPersistenceList){
        ArrayList<Offer> offers = new ArrayList<>();
        for(OfferPersistence offerPersistence: offerPersistenceList){
            offers.add(map(offerPersistence));
        }
        return  offers;
    }
}
