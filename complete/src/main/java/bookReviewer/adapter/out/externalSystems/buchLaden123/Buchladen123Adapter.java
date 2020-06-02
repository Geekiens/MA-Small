package bookReviewer.adapter.out.externalSystems.buchLaden123;


public interface Buchladen123Adapter {
    OfferApi1[] queryOffers(String isbn) throws Exception;
}
