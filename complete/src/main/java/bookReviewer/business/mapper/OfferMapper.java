package bookReviewer.business.mapper;

import bookReviewer.business.model.Offer;
import bookReviewer.entity.offerHistory.OfferHistroy;

import java.util.ArrayList;
import java.util.List;

public class OfferMapper {

    public OfferMapper() {
    }

    public ArrayList<Offer> mapCachedOfferHistoryPersistenceToOfferList(List<OfferHistroy> cachedOfferHistories) {
        ArrayList<Offer> offers = new ArrayList<>();
        for (OfferHistroy cachedOfferHistory : cachedOfferHistories) {
            offers.add(mapCachedOfferHistoryPersistenceToOffer(cachedOfferHistory));
        }
        return offers;
    }

    public Offer mapCachedOfferHistoryPersistenceToOffer(OfferHistroy cachedOfferHistory) {
        System.out.println( cachedOfferHistory.getOffers().size());
        bookReviewer.entity.offerHistory.Offer mostCurrentOffer = cachedOfferHistory.getOffers().get(cachedOfferHistory.getOffers().size() - 1);
        return new Offer(mostCurrentOffer.getPrice(),
                cachedOfferHistory.getVendor().getVendor(),
                null,
                MediaTypeMapper.mediaTypeBusiness(cachedOfferHistory.getMediaType()));
    }
}
