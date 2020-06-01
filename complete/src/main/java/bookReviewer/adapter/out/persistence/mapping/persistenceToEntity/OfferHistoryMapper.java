package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.adapter.out.persistence.model.CachedOfferHistory;
import bookReviewer.entity.offerHistory.OfferHistroy;
import bookReviewer.entity.offerHistory.Vendor;

import java.util.ArrayList;
import java.util.List;


public class OfferHistoryMapper {
    public static OfferHistroy map(CachedOfferHistory cachedOfferHistory){
        OfferHistroy offerHistroy = new OfferHistroy();
        offerHistroy.setId(cachedOfferHistory.getId());
        offerHistroy.setMediaType(MediaTypeMapper.map(cachedOfferHistory.getMediaType()));
        Vendor vendor = new Vendor();
        vendor.setVendor(cachedOfferHistory.getVendor());
        offerHistroy.setVendor(vendor);
        offerHistroy.setOffers(OfferMapper.mapList(cachedOfferHistory.getOffers()));
        return  offerHistroy;
    }

    public static List<OfferHistroy> mapList(List<CachedOfferHistory> offerHistoryPersistenceList){
        ArrayList<OfferHistroy> offerHistrories = new ArrayList<>();
        for(CachedOfferHistory cachedOfferHistory : offerHistoryPersistenceList){
            offerHistrories.add(map(cachedOfferHistory));
        }
        return offerHistrories;
    }
}
