package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.offerHistory.OfferHistroy;
import bookReviewer.entity.offerHistory.Vendor;
import bookReviewer.persistence.model.CachedOfferHistoryPersistence;

import java.util.ArrayList;
import java.util.List;


public class OfferHistoryMapper {
    public static OfferHistroy map(CachedOfferHistoryPersistence cachedOfferHistoryPersistence){
        OfferHistroy offerHistroy = new OfferHistroy();
        offerHistroy.setId(cachedOfferHistoryPersistence.getId());
        offerHistroy.setMediaType(MediaTypeMapper.map(cachedOfferHistoryPersistence.getMediaType()));
        Vendor vendor = new Vendor();
        vendor.setVendor(cachedOfferHistoryPersistence.getVendor());
        offerHistroy.setVendor(vendor);
        offerHistroy.setOffers(OfferMapper.mapList(cachedOfferHistoryPersistence.getOffers()));
        return  offerHistroy;
    }

    public static List<OfferHistroy> mapList(List<CachedOfferHistoryPersistence> offerHistoryPersistenceList){
        ArrayList<OfferHistroy> offerHistrories = new ArrayList<>();
        for(CachedOfferHistoryPersistence cachedOfferHistoryPersistence : offerHistoryPersistenceList){
            offerHistrories.add(map(cachedOfferHistoryPersistence));
        }
        return offerHistrories;
    }
}
