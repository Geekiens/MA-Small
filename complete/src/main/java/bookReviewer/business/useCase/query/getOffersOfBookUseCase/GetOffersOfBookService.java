package bookReviewer.business.useCase.query.getOffersOfBookUseCase;

import bookReviewer.business.boundary.out.externalSystems.ReceiveOffersOfBuchVerkauf24;
import bookReviewer.business.boundary.out.externalSystems.ReceiveOffersOfBuchladen123;
import bookReviewer.business.boundary.out.externalSystems.ReceiveOffersOfYourFavoriteBookVendor;
import bookReviewer.business.boundary.out.persistence.*;
import bookReviewer.business.boundary.in.useCase.query.GetOffersOfBookUseCase;
import bookReviewer.business.shared.exception.ResourceNotFoundException;
import bookReviewer.business.shared.mapper.businessToEntity.MediaTypeMapper;
import bookReviewer.entity.book.Book;
import bookReviewer.entity.offerHistory.OfferHistroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    @Autowired
    @Qualifier("ReceiveOffersOfBuchladen123Service")
    ReceiveOffersOfBuchladen123 ReceiveOffersOfBuchladen123;

    @Autowired
    @Qualifier("ReceiveOffersOfBuchVerkauf24Service")
    ReceiveOffersOfBuchVerkauf24 receiveOffersOfBuchVerkauf24;

    @Autowired
    @Qualifier("ReceiveOffersOfYourFavoriteBookVendorService")
    ReceiveOffersOfYourFavoriteBookVendor receiveOffersOfYourFavoriteBookVendor;

    public ArrayList<OfferOutput> getOffers(Long bookId) {
        String isbn = getIsbnById(bookId);
        ArrayList<OfferOutput> allOffers = new ArrayList<>();
        allOffers.addAll(getBuchladen123Offers(isbn, bookId));
        allOffers.addAll(getBuchVerkauf24Offers(isbn, bookId));
        allOffers.addAll(getYourFavoriteBookVendorOffers(isbn, bookId));

        return allOffers;

    }

    private ArrayList<OfferOutput> getCachedRequestedOffers(String isbn) {
        List<OfferHistroy> cachedOfferHistory = findAllOfferHistoriesByIsbn.findAllOffersByIsbn(isbn);
        if (cachedOfferHistory == null) return new ArrayList<>();
        return OfferOutputMapper.mapList(cachedOfferHistory);
    }

    private void cacheRequestedOffers(ArrayList<OfferOutput> offers, String isbn, Long bookId) {
        for (OfferOutput offer : offers) {
            OfferHistroy cachedOfferHistory = null;
            try {
                cachedOfferHistory = findOfferHistory.findOfferHistory(isbn,
                        offer.getVendor(),
                        MediaTypeMapper.map(offer.getMediaType()));
            } catch (Exception e){
                System.out.println("No cached offers found");
            }
            if (cachedOfferHistory == null) {
                bookReviewer.entity.offerHistory.Vendor vendor = new bookReviewer.entity.offerHistory.Vendor();
                vendor.setVendor(offer.getVendor());
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

    private ArrayList<OfferOutput> fillWithCachedOffers(String vendor, String isbn){
        ArrayList<OfferOutput> cachedOffers = getCachedRequestedOffers(isbn);
        ArrayList<OfferOutput> vendorOffers = new ArrayList<>();
        for (OfferOutput offer : cachedOffers){
            if (vendor.equalsIgnoreCase(offer.getVendor())) {
                vendorOffers.add(offer);
            }
        }
        return vendorOffers;
    }

    private ArrayList<OfferOutput> getBuchladen123Offers(String isbn, Long bookId) {
        try{
            ArrayList<OfferOutput> offers = ReceiveOffersOfBuchladen123.receiveOffers(isbn);
            cacheRequestedOffers(offers, isbn, bookId);
            return offers;
        } catch (Exception e) {
            e.printStackTrace();
            return fillWithCachedOffers(Vendor.BUCHLADEN123DE.getVendorName(), isbn);
        }
    }

    private ArrayList<OfferOutput> getBuchVerkauf24Offers(String isbn, Long bookId) {
        try {
            ArrayList<OfferOutput> offers = receiveOffersOfBuchVerkauf24.receiveOffers(isbn);
            cacheRequestedOffers(offers, isbn, bookId);
            return offers;
        } catch (Exception e) {
            System.out.println("Failed to query offers of Buch-Verkauf24.de");
            e.printStackTrace();
            return fillWithCachedOffers(Vendor.BUCHVERKAUF24.getVendorName(), isbn);

        }
    }

    private ArrayList<OfferOutput> getYourFavoriteBookVendorOffers(String isbn, Long bookId) {
        try {
            ArrayList<OfferOutput> offers = receiveOffersOfYourFavoriteBookVendor.receiveOffers(isbn);
            cacheRequestedOffers(offers, isbn, bookId);
            return offers;
        } catch (Exception e) {
            System.out.println("Failed to query offers of Buch-Verkauf24.de");
            e.printStackTrace();
            return fillWithCachedOffers(Vendor.YOUR_FAVORITE_BOOK_VENDOR.getVendorName(), isbn);
        }
    }

    private String getIsbnById(long id) {
        Book book = findBookById.findBookById(id).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + id));
        if (book == null) {
            return null;
        }
        return book.getIsbn();
    }
}
