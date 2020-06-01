package bookReviewer.business.useCase.query.getOffersOfBookUseCase;

import bookReviewer.business.shared.mapper.entityToBusiness.MediaTypeMapper;
import bookReviewer.entity.offerHistory.OfferHistroy;

import java.util.ArrayList;
import java.util.List;

public final class OfferMapper {

    public static ArrayList<OfferOutput> mapList(List<OfferHistroy> cachedOfferHistories) {
        ArrayList<OfferOutput> offers = new ArrayList<>();
        for (OfferHistroy cachedOfferHistory : cachedOfferHistories) {
            offers.add(getMostCurrentOfferAsOutput(cachedOfferHistory));
        }
        return offers;
    }

    private static OfferOutput getMostCurrentOfferAsOutput(OfferHistroy offerHistroy) {
        System.out.println( offerHistroy.getOffers().size());
        bookReviewer.entity.offerHistory.Offer mostCurrentOffer = offerHistroy.getOffers().get(offerHistroy.getOffers().size() - 1);
        return new OfferOutput(mostCurrentOffer.getPrice(),
                offerHistroy.getVendor().getVendor(),
                null,
                MediaTypeMapper.map(offerHistroy.getMediaType()));
    }
}
