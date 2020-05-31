package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.entity.offerHistory.OfferHistroy;
import bookReviewer.persistence.model.CachedOfferHistoryPersistence;

public final class OfferHistoryMapper {
    public static CachedOfferHistoryPersistence map(OfferHistroy offerHistroy, String isbn){
        CachedOfferHistoryPersistence cachedOfferHistoryPersistence = new CachedOfferHistoryPersistence();
        cachedOfferHistoryPersistence.setVendor(offerHistroy.getVendor().getVendor());
        cachedOfferHistoryPersistence.setId(offerHistroy.getId());
        cachedOfferHistoryPersistence.setMediaType(MediaTypeMapper.map(offerHistroy.getMediaType()));
        cachedOfferHistoryPersistence.setIsbn(isbn);
        cachedOfferHistoryPersistence.setOffers(OfferMapper.mapList(offerHistroy.getOffers(), cachedOfferHistoryPersistence));
        return cachedOfferHistoryPersistence;
    }
}
