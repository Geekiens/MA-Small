package bookReviewer.adapter.out.externalSystems.buchVerkauf24;

public interface BuchVerkauf24Adapter {
    OfferApi2[] queryOffers(String isbn) throws Exception;
}
