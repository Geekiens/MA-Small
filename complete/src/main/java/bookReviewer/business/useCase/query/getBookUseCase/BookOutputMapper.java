package bookReviewer.business.useCase.query.getBookUseCase;

import bookReviewer.entity.book.Book;

public class BookOutputMapper {
    public static GetBookOutput map(Book book, Double average, int total){
        GetBookOutput getBookOutput = new GetBookOutput();
        getBookOutput.setTitle(book.getBookMetaDetails().getTitle());
        getBookOutput.setAuthor(book.getBookMetaDetails().getAuthor());
        getBookOutput.setContent(book.getBookUserDetails().getContent());
        getBookOutput.setGenre(book.getBookMetaDetails().getGenre());
        getBookOutput.setId(book.getId());
        getBookOutput.setIsbn(book.getIsbn());
        getBookOutput.setKeywords(book.getBookUserDetails().getKeywords());
        getBookOutput.setLanguages(book.getBookMetaDetails().getLanguages());
        getBookOutput.setPages(book.getBookMetaDetails().getPages());
        getBookOutput.setPublisher(book.getBookMetaDetails().getPublisher());
        getBookOutput.setPublishingYear(book.getBookMetaDetails().getPublishingYear());

        getBookOutput.setAverageRating(average);
        getBookOutput.setTotalVotes(total);
        return getBookOutput;
    }
}
