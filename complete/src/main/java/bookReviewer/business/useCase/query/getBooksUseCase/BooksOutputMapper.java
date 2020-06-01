package bookReviewer.business.useCase.query.getBooksUseCase;

import bookReviewer.business.shared.model.RatingSummary;
import bookReviewer.entity.book.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksOutputMapper {
    public static GetBooksOutput map(Book book, RatingSummary ratingSummary){
        GetBooksOutput getBooksOutput = new GetBooksOutput();
        getBooksOutput.setTitle(book.getBookMetaDetails().getTitle());
        getBooksOutput.setAuthor(book.getBookMetaDetails().getAuthor());
        getBooksOutput.setContent(book.getBookUserDetails().getContent());
        getBooksOutput.setGenre(book.getBookMetaDetails().getGenre());
        getBooksOutput.setId(book.getId());
        getBooksOutput.setIsbn(book.getIsbn());
        getBooksOutput.setKeywords(book.getBookUserDetails().getKeywords());
        getBooksOutput.setLanguages(book.getBookMetaDetails().getLanguages());
        getBooksOutput.setPages(book.getBookMetaDetails().getPages());
        getBooksOutput.setPublisher(book.getBookMetaDetails().getPublisher());
        getBooksOutput.setPublishingYear(book.getBookMetaDetails().getPublishingYear());

        getBooksOutput.setAverageRating(ratingSummary.getAverageRating());
        getBooksOutput.setTotalVotes(ratingSummary.getTotalVotes());
        return getBooksOutput;
    }

}
