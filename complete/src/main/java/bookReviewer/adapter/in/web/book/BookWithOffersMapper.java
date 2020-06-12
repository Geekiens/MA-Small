package bookReviewer.adapter.in.web.book;

import bookReviewer.business.useCase.query.getBookUseCase.GetBookOutput;
import bookReviewer.business.useCase.query.getOffersOfBookUseCase.Offer;

import java.util.List;

public final class BookWithOffersMapper {

    public static BookWithOffersDTO map(GetBookOutput book, List<Offer> offers) {
        BookWithOffersDTO bookWithOffersDTO = new BookWithOffersDTO(book.getAuthor(),
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
        for (Offer offer : offers) {
            switch (offer.getMediaType()) {
                case HARDCOVER:
                    bookWithOffersDTO.addHardcoverOffer(offer);
                    break;
                case PAPERBACK:
                    bookWithOffersDTO.addPaperbackOffer(offer);
                    break;
                case EBOOK:
                    bookWithOffersDTO.addEbookOffer(offer);
                    break;
                case AUDIOBOOK:
                    bookWithOffersDTO.addAudiobookOffer(offer);
                    break;
            }
        }
        return bookWithOffersDTO;
    }
}
