package bookReviewer.adapter.in.web.book;

import bookReviewer.adapter.in.web.util.token.TokenDecoder;
import bookReviewer.application.useCase.command.createBookUseCase.Book;
import bookReviewer.application.useCase.command.createBookUseCase.CreateBookCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBookCommandMapper {

    @Autowired
    TokenDecoder tokenDecoder;

    public CreateBookCommand map(String token, NewBook newBook){
        CreateBookCommand createBookCommand = new CreateBookCommand();
        try {
            Long userId = tokenDecoder.getUserId(token);
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
