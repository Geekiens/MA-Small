package bookReviewer.adapter.out.externalSystems.yourFavoriteBookVendor;

public interface YourFavoriteBookVendorAdapter {
    OfferApi3[] queryOffers(String isbn) throws Exception;

}
