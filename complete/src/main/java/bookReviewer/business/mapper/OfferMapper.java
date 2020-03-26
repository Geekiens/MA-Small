package bookReviewer.business.Mapper;

import bookReviewer.business.model.Offer;
import bookReviewer.persistence.model.CachedOfferHistoryPersistence;
import bookReviewer.persistence.model.OfferPersistence;

import java.util.ArrayList;
import java.util.List;

public class OfferMapper {

    public OfferMapper() {
    }

    public ArrayList<Offer> mapCachedOfferHistoryPersistenceToOfferList(List<CachedOfferHistoryPersistence> cachedOfferHistories) {
        ArrayList<Offer> offers = new ArrayList<>();
        for (CachedOfferHistoryPersistence cachedOfferHistory : cachedOfferHistories) {
            offers.add(mapCachedOfferHistoryPersistenceToOffer(cachedOfferHistory));
        }
        return offers;
    }

    public Offer mapCachedOfferHistoryPersistenceToOffer(CachedOfferHistoryPersistence cachedOfferHistory) {
        System.out.println( cachedOfferHistory.getOffers().size());
        OfferPersistence mostCurrentOffer = cachedOfferHistory.getOffers().get(cachedOfferHistory.getOffers().size() - 1);
        return new Offer(mostCurrentOffer.getPrice(), cachedOfferHistory.getVendor(), null, cachedOfferHistory.getMediaType());
    }
}
