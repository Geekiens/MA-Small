package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.entity.offerHistory.Offer;
import bookReviewer.entity.offerHistory.OfferHistroy;
import bookReviewer.persistence.model.CachedOfferHistoryPersistence;
import bookReviewer.persistence.model.OfferPersistence;

import java.util.ArrayList;

public final class OfferMapper {
    public static OfferPersistence map(Offer offer, CachedOfferHistoryPersistence cachedOfferHistoryPersistence){
        OfferPersistence offerPersistence = new OfferPersistence();
        offerPersistence.setPrice(offer.getPrice());
        offerPersistence.setRequestDate(offer.getRequestDate());
        offerPersistence.setCachedOfferHistoryPersistence(cachedOfferHistoryPersistence);
        return offerPersistence;
    }

    public static ArrayList<OfferPersistence> mapList(ArrayList<Offer> offers, CachedOfferHistoryPersistence cachedOfferHistoryPersistence){
        ArrayList<OfferPersistence> offerPersistences = new ArrayList<>();
        for (Offer offer : offers){
            offerPersistences.add(map(offer, cachedOfferHistoryPersistence));
        }
        return offerPersistences;
    }
}
