package bookReviewer.adapter.out.externalSystems.yourFavoriteBookVendor;


import bookReviewer.adapter.out.externalSystems.HttpClientFactory;
import bookReviewer.business.boundary.out.externalSystems.ReceiveOffersOfYourFavoriteBookVendor;
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
@Qualifier("ReceiveOffersOfYourFavoriteBookVendorService")
public class ReceiveOffersOfYourFavoriteBookVendorService implements ReceiveOffersOfYourFavoriteBookVendor {
    public ArrayList<OfferOutput> receiveOffers(String isbn) throws Exception {
        RestTemplate restTemplate = new RestTemplate(HttpClientFactory.getClientHttpRequestFactory());

        ResponseEntity<OfferApi3[]> response = restTemplate.getForEntity(
                "http://localhost:9092/offer/" + isbn,
                OfferApi3[].class);
        if (response.getStatusCode().isError()) throw new Exception();

        OfferApi3[] offerApi3s = response.getBody();

        ArrayList<OfferOutput> offers = offerApi3tToOfferMapper(offerApi3s, isbn);
        return offers;
    }

    private static ArrayList<OfferOutput> offerApi3tToOfferMapper(OfferApi3[] offerApi3s, String isbn) {
        ArrayList<OfferOutput> offers = new ArrayList<>();
        for (OfferApi3 offerApi3 : offerApi3s) {
            BigDecimal price = offerApi3.getPrice();
            if (offerApi3.getCurrency().equals("USD")) {
                price.multiply(CurrencyApi.getCurrencyExchange());
                price = price.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            }
            // add shipping costs for vendor
            if (price.doubleValue() < 20.00 && (offerApi3.getType() == 0 || offerApi3.getType() == 1)) {
                price.add(new BigDecimal(2.00));
            }
            MediaType mediaType = mapMediaTypeOfYourFavoriteBookVendor(offerApi3);

            OfferOutput offer = new OfferOutput(price,
                    Vendor.YOUR_FAVORITE_BOOK_VENDOR.getVendorName(),
                    "www.your-favorite-book-vendor.com/bookreviewer/" + isbn,
                    mediaType);
            offers.add(offer);
        }
        return offers;
    }


    private static MediaType mapMediaTypeOfYourFavoriteBookVendor(OfferApi3 offerApi3) {
        return mapNumberToMediaType(offerApi3.getType());
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
