package bookReviewer.adapter.out.externalSystems;


import bookReviewer.business.boundary.out.externalSystems.ReceiveOffersOfBuchVerkauf24;
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
@Qualifier("ReceiveOffersOfBuchVerkauf24Service")
public class ReceiveOffersOfBuchVerkauf24Service implements ReceiveOffersOfBuchVerkauf24 {
    public ArrayList<OfferOutput> receiveOffers(String isbn) throws Exception {
        RestTemplate restTemplate = new RestTemplate(HttpClientFactory.getClientHttpRequestFactory());
        ResponseEntity<OfferApi2[]> response = restTemplate.getForEntity(
                "http://localhost:9091/offer/" + isbn,
                OfferApi2[].class);
        if (response.getStatusCode().isError()) throw new Exception();
        OfferApi2[] offerApi2s = response.getBody();
        ArrayList<OfferOutput> offers = offerApi2tToOfferMapper(offerApi2s, isbn);
        return offers;
    }

    private static ArrayList<OfferOutput> offerApi2tToOfferMapper(OfferApi2[] offerApi2s, String isbn) {
        ArrayList<OfferOutput> offers = new ArrayList<>();
        for (OfferApi2 offerApi2 : offerApi2s) {
            if (!offerApi2.isAvailable()) {
                continue;
            }
            BigDecimal price = offerApi2.getPrice().add(offerApi2.getShippingFee());
            MediaType mediaType = mapMediaTypeOfBuchVerkauf24(offerApi2);

            OfferOutput offer = new OfferOutput(price,
                    Vendor.BUCHVERKAUF24.getVendorName(),
                    "www.buch-verkauf24.de/bookreviewer/" + isbn,
                    mediaType);
            offers.add(offer);
        }
        return offers;
    }

    private static MediaType mapMediaTypeOfBuchVerkauf24(OfferApi2 offerApi2) {
        return mapNumberToMediaType(offerApi2.getType());
    }

    private static MediaType mapNumberToMediaType(int type) {
        MediaType mediaType;
        switch (type) {
            case 0:
                mediaType = MediaType.HARDCOVER;
                break;
            case 1:
                mediaType = MediaType.PAPERBACK;
                break;
            case 2:
                mediaType = MediaType.EBOOK;
                break;
            case 3:
                mediaType = MediaType.AUDIOBOOK;
                break;
            default:
                mediaType = null;
                break;
        }
        return mediaType;
    }
}

