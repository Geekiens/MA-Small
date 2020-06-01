package bookReviewer.adapter.in.web.book;

import bookReviewer.business.useCase.query.getBookUseCase.GetBookOutput;
import bookReviewer.business.useCase.query.getOffersOfBookUseCase.OfferOutput;

import java.util.List;

public final class BookWithOffersMapper {

    public static BookWithOffers map(GetBookOutput book, List<OfferOutput> offers) {
        BookWithOffers bookWithOffers = new BookWithOffers(book.getAuthor(),
                book.getTitle(),
                book.getGenre(),
                book.getKeywords(),
                book.getLanguages(),
                book.getPublisher(),
                book.getPages(),
                book.getIsbn(),
                book.getPublishingYear(),
                book.getContent(),
                book.getTotalVotes(),
                book.getAverageRating()
        );
        for (OfferOutput offer : offers) {
            switch (offer.getMediaType()) {
                case HARDCOVER:
                    bookWithOffers.addHardcoverOffer(offer);
                    break;
                case PAPERBACK:
                    bookWithOffers.addPaperbackOffer(offer);
                    break;
                case EBOOK:
                    bookWithOffers.addEbookOffer(offer);
                    break;
                case AUDIOBOOK:
                    bookWithOffers.addAudiobookOffer(offer);
                    break;
            }
        }
        return bookWithOffers;
    }
}
