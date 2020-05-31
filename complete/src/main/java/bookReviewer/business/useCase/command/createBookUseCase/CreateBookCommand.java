package bookReviewer.business.useCase.command.createBookUseCase;

public class CreateBookCommand {
    private Long userId;
    private Book book;
    private String token;
    public CreateBookCommand() {
    }

    public CreateBookCommand(Long userId, Book book, String token) {
        this.userId = userId;
        this.book = book;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
