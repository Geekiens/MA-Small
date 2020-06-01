package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.adapter.out.persistence.model.CachedOfferHistory;
import bookReviewer.entity.offerHistory.OfferHistroy;

public final class OfferHistoryMapper {
    public static CachedOfferHistory map(OfferHistroy offerHistroy, String isbn){
        CachedOfferHistory cachedOfferHistory = new CachedOfferHistory();
        cachedOfferHistory.setVendor(offerHistroy.getVendor().getVendor());
        cachedOfferHistory.setId(offerHistroy.getId());
        cachedOfferHistory.setMediaType(MediaTypeMapper.map(offerHistroy.getMediaType()));
        cachedOfferHistory.setIsbn(isbn);
        cachedOfferHistory.setOffers(OfferMapper.mapList(offerHistroy.getOffers(), cachedOfferHistory));
        return cachedOfferHistory;
    }
}
