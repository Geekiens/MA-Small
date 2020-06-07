package bookReviewer.application.useCase.command.createBookUseCase;

public class CreateBookCommand {
    private Long userId;
    private Book book;

    public CreateBookCommand() {
    }

    public CreateBookCommand(Long userId, Book book) {
        this.userId = userId;
        this.book = book;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
