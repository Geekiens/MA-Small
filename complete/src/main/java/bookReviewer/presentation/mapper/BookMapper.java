package bookReviewer.presentation.mapper;

import bookReviewer.business.model.Offer;
import bookReviewer.persistence.model.Book;
import bookReviewer.presentation.model.BookPresentation;

import java.util.List;

public class BookMapper {
    public BookMapper() {
    }

    public BookPresentation map(Book book, List<Offer> offers){

        BookPresentation bookPresentation = new BookPresentation(book.getAuthor(),
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

        for (Offer offer : offers) {
            switch (offer.getMediaType()) {
                case HARDCOVER: bookPresentation.addHardcoverOffer(offer); break;
                case PAPERBACK: bookPresentation.addPaperbackOffer(offer); break;
                case EBOOK: bookPresentation.addEbookOffer(offer); break;
                case AUDIOBOOK: bookPresentation.addAudiobookOffer(offer); break;
            }
        }
        return bookPresentation;
    }
    }
q