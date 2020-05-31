package bookReviewer.business.useCase.query.getOffersOfBookUseCase;

import bookReviewer.business.boundary.out.persistence.*;
import bookReviewer.business.boundary.in.useCase.query.GetOffersOfBookUseCase;
import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.business.mapper.OfferMapper;
import bookReviewer.business.mapper.businessToEntity.MediaTypeMapper;
import bookReviewer.business.mapper.entityToBusiness.BookMapper;
import bookReviewer.business.model.*;
import bookReviewer.business.shared.model.MediaType;
import bookReviewer.entity.offerHistory.OfferHistroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("GetOffersOfBookService")
public class GetOffersOfBookService implements GetOffersOfBookUseCase {
    @Autowired
    @Qualifier("FindBookByIdService")
    FindBookById findBookById;

    @Autowired
    @Qualifier("FindAllOfferHistoriesByIsbnService")
    FindAllOfferHistoriesByIsbn findAllOfferHistoriesByIsbn;

    @Autowired
    @Qualifier("FindOfferHistoryService")
    FindOfferHistory findOfferHistory;

    @Autowired
    @Qualifier("SaveOfferHistoryService")
    SaveOfferHistory saveOfferHistory;

    private OfferMapper offerMapper = new OfferMapper();


    public ArrayList<Offer> getOffers(Long bookId) {
        String isbn = getIsbnById(bookId);
        ArrayList<Offer> allOffers = new ArrayList<Offer>();
        allOffers.addAll(getBuchladen123Offers(isbn, bookId));
        allOffers.addAll(getBuchVerkauf24Offers(isbn, bookId));
        allOffers.addAll(getYourFavoriteBookVendorOffers(isbn, bookId));

        return allOffers;

    }

    private ArrayList<Offer> getCachedRequestedOffers(String isbn) {
        List<OfferHistroy> cachedOfferHistory = findAllOfferHistoriesByIsbn.findAllOffersByIsbn(isbn);
        if (cachedOfferHistory == null) return new ArrayList<>();
        return offerMapper.mapCachedOfferHistoryPersistenceToOfferList(cachedOfferHistory);
    }

    private void cacheRequestedOffers(ArrayList<Offer> offers, String isbn, Long bookId) {
        for (Offer offer : offers) {
            OfferHistroy cachedOfferHistory = null;
            try {
                cachedOfferHistory = findOfferHistory.findOfferHistory(isbn,
                        offer.getVendor(),
                        bookReviewer.business.mapper.businessToEntity.MediaTypeMapper.map(offer.getMediaType()));
            } catch (Exception e){
                System.out.println("No cached offers found");
            }
            if (cachedOfferHistory == null) {
                bookReviewer.entity.offerHistory.Vendor vendor = new bookReviewer.entity.offerHistory.Vendor();
                vendor.setVendor(offer.getVendor());
                // long bookId, ArrayList<Offer> offers, Vendor vendor, MediaType mediaType
                OfferHistroy newCachedOfferHistory = new OfferHistroy(
                        bookId,
                        new ArrayList<>(),
                        vendor,
                        MediaTypeMapper.map(offer.getMediaType()));
                bookReviewer.entity.offerHistory.Offer newOffer = new bookReviewer.entity.offerHistory.Offer(offer.getPrice(), LocalDate.now());
                newCachedOfferHistory.addOffer(newOffer);

                saveOfferHistory.saveOfferHistory(newCachedOfferHistory);
                return;
            }
            bookReviewer.entity.offerHistory.Offer mostCurrentOffer = cachedOfferHistory.getOffers().get(cachedOfferHistory.getOffers().size() -1);
            if (mostCurrentOffer.getRequestDate() != LocalDate.now() || mostCurrentOffer.getPrice() != offer.getPrice()) {
                bookReviewer.entity.offerHistory.Offer newCachedOffer = new bookReviewer.entity.offerHistory.Offer(offer.getPrice(), LocalDate.now());
                cachedOfferHistory.addOffer(newCachedOffer);
                saveOfferHistory.saveOfferHistory(cachedOfferHistory);
            }
        }
    }

    private ArrayList<Offer> fillWithCachedOffers(String vendor, String isbn){
        ArrayList<Offer> cachedOffers = getCachedRequestedOffers(isbn);
        ArrayList<Offer> vendorOffers = new ArrayList<>();
        for (Offer offer : cachedOffers){
            if (vendor.equalsIgnoreCase(offer.getVendor())) {
                vendorOffers.add(offer);
            }
        }
        return vendorOffers;
    }

    private ArrayList<Offer> getBuchladen123Offers(String isbn, Long bookId) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        try {
            ResponseEntity<OfferApi1[]> response = restTemplate.getForEntity(
                    "http://localhost:9090/offer?isbn=" + isbn,
                    OfferApi1[].class);
            OfferApi1[] offerApi1s = response.getBody();
            System.out.println(response.getStatusCode().isError());
            if (response.getStatusCode().isError()) throw new Exception();
            ArrayList<Offer> offers = offerApi1tToOfferMapper(offerApi1s);
            cacheRequestedOffers(offers, isbn, bookId);
            return offers;

        } catch (Exception e) {
            e.printStackTrace();
            return fillWithCachedOffers(Vendor.BUCHLADEN123DE.getVendorName(), isbn);
        }
    }

    private ArrayList<Offer> offerApi1tToOfferMapper(OfferApi1[] offerApi1s) {
        ArrayList<Offer> offers = new ArrayList<>();
        for (OfferApi1 offerApi1 : offerApi1s) {
            BigDecimal price = new BigDecimal(offerApi1.getPrice()).divide(new BigDecimal(100));

            Offer offer = new Offer(price,
                    Vendor.BUCHLADEN123DE.getVendorName(),
                    offerApi1.getAffiliate(),
                    MediaType.valueOf(offerApi1.getMedia().toUpperCase()));
            offers.add(offer);
        }
        return offers;
    }

    private ArrayList<Offer> getBuchVerkauf24Offers(String isbn, Long bookId) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        try {
            ResponseEntity<OfferApi2[]> response = restTemplate.getForEntity(
                    "http://localhost:9091/offer/" + isbn,
                    OfferApi2[].class);
            if (response.getStatusCode().isError()) throw new Exception();
            OfferApi2[] offerApi2s = response.getBody();
            ArrayList<Offer> offers = offerApi2tToOfferMapper(offerApi2s, isbn);
            cacheRequestedOffers(offers, isbn, bookId);
            return offers;
        } catch (Exception e) {
            System.out.println("Failed to query offers of Buch-Verkauf24.de");
            e.printStackTrace();
            return fillWithCachedOffers(Vendor.BUCHVERKAUF24.getVendorName(), isbn);

        }
    }

    private ArrayList<Offer> offerApi2tToOfferMapper(OfferApi2[] offerApi2s, String isbn) {
        ArrayList<Offer> offers = new ArrayList<>();
        for (OfferApi2 offerApi2 : offerApi2s) {
            if (!offerApi2.isAvailable()) { continue; }
            BigDecimal price = offerApi2.getPrice().add(offerApi2.getShippingFee());
            MediaType mediaType = mapMediaTypeOfBuchVerkauf24(offerApi2);

            Offer offer = new Offer(price,
                    Vendor.BUCHVERKAUF24.getVendorName(),
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
                mediaType = null;
                break;
        }
        return mediaType;
    }

    private ArrayList<Offer> getYourFavoriteBookVendorOffers(String isbn, Long bookId) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        try {
            ResponseEntity<OfferApi3[]> response = restTemplate.getForEntity(
                    "http://localhost:9092/offer/" + isbn,
                    OfferApi3[].class);
            if (response.getStatusCode().isError()) throw new Exception();

            OfferApi3[] offerApi3s = response.getBody();

            ArrayList<Offer> offers = offerApi3tToOfferMapper(offerApi3s, isbn);
            cacheRequestedOffers(offers, isbn, bookId);
            return offers;
        } catch (Exception e) {
            System.out.println("Failed to query offers of Buch-Verkauf24.de");
            e.printStackTrace();
            return fillWithCachedOffers(Vendor.YOUR_FAVORITE_BOOK_VENDOR.getVendorName(), isbn);
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
                    Vendor.YOUR_FAVORITE_BOOK_VENDOR.getVendorName(),
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

    private String getIsbnById(long id) {
        BookBusiness book = BookMapper.map(findBookById.findBookById(id).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + id)));
        if (book == null) {
            return null;
        }
        return book.getIsbn();
    }
}
