package bookReviewer.adapter.out.externalSystems.yourFavoriteBookVendor;


import bookReviewer.adapter.out.externalSystems.currencyApi.CurrencyApi;
import bookReviewer.application.boundary.out.externalSystems.ReceiveOffersOfYourFavoriteBookVendor;
import bookReviewer.application.shared.model.MediaType;
import bookReviewer.application.useCase.query.getOffersOfBookUseCase.OfferOutput;
import bookReviewer.application.useCase.query.getOffersOfBookUseCase.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@Qualifier("ReceiveOffersOfYourFavoriteBookVendorService")
public class ReceiveOffersOfYourFavoriteBookVendorService implements ReceiveOffersOfYourFavoriteBookVendor {

    @Autowired
    @Qualifier("YourFavoriteBookVendorAdapterService")
    YourFavoriteBookVendorAdapter yourFavoriteBookVendorAdapter;

    @Autowired
    CurrencyApi currencyApi;

    public ArrayList<OfferOutput> receiveOffers(String isbn) throws Exception {
        return offerApi3tToOfferMapper(yourFavoriteBookVendorAdapter.queryOffers(isbn), isbn);
    }

    private ArrayList<OfferOutput> offerApi3tToOfferMapper(OfferApi3[] offerApi3s, String isbn) {
        ArrayList<OfferOutput> offers = new ArrayList<>();
        for (OfferApi3 offerApi3 : offerApi3s) {
            BigDecimal price = offerApi3.getPrice();
            if (offerApi3.getCurrency().equals("USD")) {
                price.multiply(currencyApi.getCurrencyExchange());
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
