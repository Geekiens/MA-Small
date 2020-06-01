package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.adapter.out.persistence.model.CachedOfferHistory;
import bookReviewer.adapter.out.persistence.model.Offer;

import java.util.ArrayList;

public final class OfferMapper {
    public static Offer map(bookReviewer.entity.offerHistory.Offer offer, CachedOfferHistory cachedOfferHistory){
        Offer offerPersistence = new Offer();
        offerPersistence.setPrice(offer.getPrice());
        offerPersistence.setRequestDate(offer.getRequestDate());
        offerPersistence.setCachedOfferHistory(cachedOfferHistory);
        return offerPersistence;
    }

    public static ArrayList<Offer> mapList(ArrayList<bookReviewer.entity.offerHistory.Offer> offers, CachedOfferHistory cachedOfferHistory){
        ArrayList<Offer> offerPersistences = new ArrayList<>();
        for (bookReviewer.entity.offerHistory.Offer offer : offers){
            offerPersistences.add(map(offer, cachedOfferHistory));
        }
        return offerPersistences;
    }
}
