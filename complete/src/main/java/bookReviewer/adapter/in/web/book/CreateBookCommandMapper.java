package bookReviewer.adapter.in.web.book;

import bookReviewer.adapter.in.web.util.TokenDecoder;
import bookReviewer.business.useCase.command.createBookUseCase.Book;
import bookReviewer.business.useCase.command.createBookUseCase.CreateBookCommand;

public final class CreateBookCommandMapper {

    public static CreateBookCommand map(String token, NewBookDTO newBookDTO){
        CreateBookCommand createBookCommand = new CreateBookCommand();
        try {
            Long userId = TokenDecoder.getUserId(token);
            createBookCommand.setUserId(userId);
        } catch (Exception e){
            System.out.println("Guest creates Book");
        }
        Book book = mapBook(newBookDTO);
        createBookCommand.setBook(book);
        return createBookCommand;
    }

    private static Book mapBook(NewBookDTO newBookDTO) {
        Book book = new Book();
        book.setAuthor(newBookDTO.getAuthor());
        book.setTitle(newBookDTO.getTitle());
        book.setGenre(newBookDTO.getGenre());
        book.setKeywords(newBookDTO.getKeywords());
        book.setLanguages(newBookDTO.getLanguages());
        book.setPublisher(newBookDTO.getPublisher());
        book.setPages(newBookDTO.getPages());
        book.setIsbn(newBookDTO.getIsbn());
        book.setPublishingYear(newBookDTO.getPublishingYear());
        book.setContent(newBookDTO.getContent());
        return book;
    }
}
