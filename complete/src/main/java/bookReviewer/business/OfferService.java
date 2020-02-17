package bookReviewer.business;

import bookReviewer.business.model.*;

import bookReviewer.persistence.model.OfferPersistence;
import bookReviewer.persistence.repository.OfferRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class OfferService {

    BookService bookService;

    OfferRepository offerRepository;

    public ArrayList<Offer> getOffers(Long id) {
        String isbn = bookService.getIsbnById(id);
        ArrayList<Offer> allOffers = new ArrayList<Offer>();
        allOffers.addAll(getBuchladen123Offers(isbn));
        allOffers.addAll(getBuchVerkauf24Offers(isbn));
        allOffers.addAll(getYourFavoriteBookVendorOffers(isbn));
        return allOffers;
    }

    private void cacheRequestedOffers(ArrayList<Offer> offers) {
        LocalDate date =LocalDate.now();
        for (Offer offer : offers) {
            OfferPersistence offerPersistence = new OfferPersistence(offer.getPrice(), date, offer.getVendor(), offer.getMediaType());
            // Create if not exists, load all offers for isbn before
        }
    }

    private ArrayList<Offer> getBuchladen123Offers(String isbn) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        try {
            ResponseEntity<OfferApi1[]> response = restTemplate.getForEntity(
                    "http://localhost:9090/offer?isbn=123-12-12345-12-1",
                    OfferApi1[].class);
            OfferApi1[] offerApi1s = response.getBody();
            return offerApi1tToOfferMapper(offerApi1s);

        } catch (Exception e) {
            System.out.println("Failed to query offers of Buchladen123.de");
            e.printStackTrace();
            // TODO: get cached offers of this vendor
            return new ArrayList<>();
        }
    }

    private ArrayList<Offer> offerApi1tToOfferMapper(OfferApi1[] offerApi1s) {
        ArrayList<Offer> offers = new ArrayList<>();
        for (OfferApi1 offerApi1 : offerApi1s) {
            BigDecimal price = new BigDecimal(offerApi1.getPrice()).divide(new BigDecimal(100));

            Offer offer = new Offer(price,
                    "Buchladen123.de",
                    offerApi1.getAffiliate(),
                    MediaType.valueOf(offerApi1.getMedia().toUpperCase()));
            offers.add(offer);
        }
        return offers;
    }

    private ArrayList<Offer> getBuchVerkauf24Offers(String isbn) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        try {
            ResponseEntity<OfferApi2[]> response = restTemplate.getForEntity(
                    "http://localhost:9091/offer/123-12-12345-12-1",
                    OfferApi2[].class);
            OfferApi2[] offerApi2s = response.getBody();
            return offerApi2tToOfferMapper(offerApi2s, isbn);

        } catch (Exception e) {
            System.out.println("Failed to query offers of Buch-Verkauf24.de");
            e.printStackTrace();
            // TODO: get cached offers of this vendor
            return new ArrayList<>();
        }
    }

    private ArrayList<Offer> offerApi2tToOfferMapper(OfferApi2[] offerApi2s, String isbn) {
        ArrayList<Offer> offers = new ArrayList<>();
        for (OfferApi2 offerApi2 : offerApi2s) {
            if (!offerApi2.isAvailable()) { continue; }
            BigDecimal price = offerApi2.getPrice().add(offerApi2.getShippingFee());
            MediaType mediaType = mapMediaTypeOfBuchVerkauf24(offerApi2);

            Offer offer = new Offer(price,
                    "Buchverkauf 24",
                    "www.buch-verkauf24.de/bookreviewer/" + isbn,
                    mediaType);
            offers.add(offer);
        }
        return offers;
    }

    private MediaType mapMediaTypeOfBuchVerkauf24(OfferApi2 offerApi2) {
        return mapNumberToMediaType(offerApi2.getType());
    }

    private MediaType mapNumberToMediaType(int type) {
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
                System.out.println("No mediaType assigned - Using hardcover as default");
                mediaType = MediaType.HARDCOVER;
                break;
        }
        return mediaType;
    }

    private ArrayList<Offer> getYourFavoriteBookVendorOffers(String isbn) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        try {
            ResponseEntity<OfferApi3[]> response = restTemplate.getForEntity(
                    "http://localhost:9092/offer/123-12-12345-12-1",
                    OfferApi3[].class);
            OfferApi3[] offerApi3s = response.getBody();
            return offerApi3tToOfferMapper(offerApi3s, isbn);

        } catch (Exception e) {
            System.out.println("Failed to query offers of Buch-Verkauf24.de");
            e.printStackTrace();
            // TODO: get cached offers of this vendor
            return new ArrayList<>();
        }
    }

    private ArrayList<Offer> offerApi3tToOfferMapper(OfferApi3[] offerApi3s, String isbn) {
        ArrayList<Offer> offers = new ArrayList<>();
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

            Offer offer = new Offer(price,
                    "Your favorite book vendor",
                    "www.your-favorite-book-vendor.com/bookreviewer/" + isbn,
                    mediaType);
            offers.add(offer);
        }
        return offers;
    }

    private BigDecimal getCurrencyExchange() {
        BigDecimal exchangeRate = new BigDecimal(1);
        try {
            RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

            CurrencyExchange currencyExchange = restTemplate.getForObject(
                    "http://localhost:9080/latest?base=USD ",
                    CurrencyExchange.class);
            // TODO: safe currency rate in db
            return currencyExchange.getRates().getEUR();
        } catch (Exception e) {
            System.out.println("Failed to query exchange rate");
            e.printStackTrace();
            // TODO: get cached exchange rate from db
            return exchangeRate;
        }
    }

    private MediaType mapMediaTypeOfYourFavoriteBookVendor(OfferApi3 offerApi3) {
        return mapNumberToMediaType(offerApi3.getType());
    }

    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(8000);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(8000);
        return clientHttpRequestFactory;
    }
}



