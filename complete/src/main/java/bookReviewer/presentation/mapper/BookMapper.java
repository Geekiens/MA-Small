package bookReviewer.presentation.mapper;

import bookReviewer.business.model.Offer;
import bookReviewer.persistence.model.Book;
import bookReviewer.presentation.model.BookDetailPresentation;

import java.util.List;

public class BookMapper {
    public BookMapper() {
    }

    public BookDetailPresentation map(Book book, List<Offer> offers) {

        BookDetailPresentation bookDetailPresentation = new BookDetailPresentation(book.getAuthor(),
                book.getTitle(),
                book.getGenre(),
                book.getKeywords(),
                book.getLanguages(),
                book.getPublisher(),
                book.getPages(),
                book.getIsbn(),
                book.getPublishingYear(),
                book.getContent()
        );
        System.out.println(bookDetailPresentation);
        for (Offer offer : offers) {
            System.out.println(offer);
            switch (offer.getMediaType()) {
                case HARDCOVER:
                    bookDetailPresentation.addHardcoverOffer(offer);
                    break;
                case PAPERBACK:
                    bookDetailPresentation.addPaperbackOffer(offer);
                    break;
                case EBOOK:
                    bookDetailPresentation.addEbookOffer(offer);
                    break;
                case AUDIOBOOK:
                    bookDetailPresentation.addAudiobookOffer(offer);
                    break;
            }
        }
        return bookDetailPresentation;
    }
}
