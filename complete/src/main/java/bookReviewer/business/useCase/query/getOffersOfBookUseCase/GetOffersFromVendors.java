package bookReviewer.business.useCase.query.getOffersOfBookUseCase;

import bookReviewer.business.shared.model.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;

public final class GetOffersFromVendors {
    public static ArrayList<OfferOutput> getOffersFromBuchladen123(String isbn) throws Exception {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
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

    public static ArrayList<OfferOutput> getOffersFromBuchVerkauf24(String isbn) throws Exception {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
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

    public static ArrayList<OfferOutput> getOffersFromYourFavoriteBookVendor(String isbn) throws Exception {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

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
                price.multiply(getCurrencyExchange());
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

    private static BigDecimal getCurrencyExchange() {
        BigDecimal exchangeRate = new BigDecimal(1);
        try {
            RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

            CurrencyExchange currencyExchange = restTemplate.getForObject(
                    "http://localhost:9080/latest?base=USD ",
                    CurrencyExchange.class);
            return currencyExchange.getRates().getEUR();
        } catch (Exception e) {
            System.out.println("Failed to query exchange rate");
            e.printStackTrace();
            return exchangeRate;
        }
    }

    private static MediaType mapMediaTypeOfYourFavoriteBookVendor(OfferApi3 offerApi3) {
        return mapNumberToMediaType(offerApi3.getType());
    }

    private static SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(8000);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(8000);
        return clientHttpRequestFactory;
    }
}

