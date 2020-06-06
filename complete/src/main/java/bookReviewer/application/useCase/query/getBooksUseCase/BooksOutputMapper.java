package bookReviewer.application.useCase.query.getBooksUseCase;

import bookReviewer.entity.book.Book;

public class BooksOutputMapper {
    public static GetBooksOutput map(Book book, Double average, int total){
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

        getBooksOutput.setAverageRating(average);
        getBooksOutput.setTotalVotes(total);
        return getBooksOutput;
    }

}
