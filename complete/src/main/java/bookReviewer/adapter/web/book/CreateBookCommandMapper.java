package bookReviewer.adapter.web.book;

import bookReviewer.adapter.web.util.TokenDecoder;
import bookReviewer.business.useCase.command.createBookUseCase.Book;
import bookReviewer.business.useCase.command.createBookUseCase.CreateBookCommand;

public final class CreateBookCommandMapper {

    public static CreateBookCommand map(String token, NewBook newBook){
        CreateBookCommand createBookCommand = new CreateBookCommand();
        try {
            Long userId = TokenDecoder.getUserId(token);
            createBookCommand.setUserId(userId);
        } catch (Exception e){
            System.out.println("Guest creates Book");
        }
        Book book = mapBook(newBook);
        createBookCommand.setBook(book);
        return createBookCommand;
    }

    private static Book mapBook(NewBook newBook) {
        Book book = new Book();
        book.setAuthor(newBook.getAuthor());
        book.setTitle(newBook.getTitle());
        book.setGenre(newBook.getGenre());
        book.setKeywords(newBook.getKeywords());
        book.setLanguages(newBook.getLanguages());
        book.setPublisher(newBook.getPublisher());
        book.setPages(newBook.getPages());
        book.setIsbn(newBook.getIsbn());
        book.setPublishingYear(newBook.getPublishingYear());
        book.setContent(newBook.getContent());
        return book;
    }
}
