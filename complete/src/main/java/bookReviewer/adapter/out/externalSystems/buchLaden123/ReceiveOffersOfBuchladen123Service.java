package bookReviewer.adapter.out.externalSystems;


import bookReviewer.business.boundary.out.externalSystems.ReceiveOffersOfBuchladen123;
import bookReviewer.business.shared.model.MediaType;
import bookReviewer.business.useCase.query.getOffersOfBookUseCase.OfferOutput;
import bookReviewer.business.useCase.query.getOffersOfBookUseCase.Vendor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@Qualifier("ReceiveOffersOfBuchladen123Service")
public class ReceiveOffersOfBuchladen123Service implements ReceiveOffersOfBuchladen123 {
    public ArrayList<OfferOutput> receiveOffers(String isbn) throws Exception {
        RestTemplate restTemplate = new RestTemplate(HttpClientFactory.getClientHttpRequestFactory());
        ResponseEntity<OfferApi1[]> response = restTemplate.getForEntity(
                "http://localhost:9090/offer?isbn=" + isbn,
                OfferApi1[].class);
        OfferApi1[] offerApi1s = response.getBody();
        System.out.println(response.getStatusCode().isError());
        if (response.getStatusCode().isError()) throw new Exception();
        ArrayList<OfferOutput> offers = offerApi1tToOfferMapper(offerApi1s);
        return offers;
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



