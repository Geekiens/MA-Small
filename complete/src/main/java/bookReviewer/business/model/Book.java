package bookReviewer.business.model;

public class Book extends bookReviewer.persistence.model.Book {
    Offer[] offers;

    Book() {
        super();
    }

    public Offer[] getOffers() {
        return offers;
    }

    public void setOffers(Offer[] offers) {
        this.offers = offers;
    }
}
