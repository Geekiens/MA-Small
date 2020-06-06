package bookReviewer.adapter.out.externalSystems.buchLaden123;

import bookReviewer.application.boundary.out.externalSystems.ReceiveOffersOfBuchladen123;
import bookReviewer.application.shared.model.MediaType;
import bookReviewer.application.useCase.query.getOffersOfBookUseCase.OfferOutput;
import bookReviewer.application.useCase.query.getOffersOfBookUseCase.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@Qualifier("ReceiveOffersOfBuchladen123Service")
public class ReceiveOffersOfBuchladen123Service implements ReceiveOffersOfBuchladen123 {

    @Autowired
    @Qualifier("Buchladen123AdapterService")
    Buchladen123Adapter buchladen123Adapter;

    public ArrayList<OfferOutput> receiveOffers(String isbn) throws Exception {
        return offerApi1tToOfferMapper(buchladen123Adapter.queryOffers(isbn));
    }

    private static ArrayList<OfferOutput> offerApi1tToOfferMapper(OfferApi1[] offerApi1s) {
        ArrayList<OfferOutput> offers = new ArrayList<>();
        for (OfferApi1 offerApi1 : offerApi1s) {
            BigDecimal price = new BigDecimal(offerApi1.getPrice()).divide(new BigDecimal(100));

            OfferOutput offer = new OfferOutput(price,
                    Vendor.BUCHLADEN123DE.getVendorName(),
                    offerApi1.getAffiliate(),
                    MediaType.valueOf(offerApi1.getMedia().toUpperCase()));
            offers.add(offer);
        }
        return offers;
    }
}
