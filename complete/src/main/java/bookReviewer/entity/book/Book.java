package bookReviewer.entity.book;

import java.util.Objects;

public class Book {
    private long id;
    private String isbn;
    private BookMetaDetails bookMetaDetails;
    private  BookUserDetails bookUserDetails;

    public Book() {
    }

    public Book(long id, String isbn, BookMetaDetails bookMetaDetails, BookUserDetails bookUserDetails) {
        this.id = id;
        this.isbn = isbn;
        this.bookMetaDetails = bookMetaDetails;
        this.bookUserDetails = bookUserDetails;
    }

    public Book(BookMetaDetails bookMetaDetails, BookUserDetails bookUserDetails) {
        this.bookMetaDetails = bookMetaDetails;
        this.bookUserDetails = bookUserDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookMetaDetails getBookMetaDetails() {
        return bookMetaDetails;
    }

    public void setBookMetaDetails(BookMetaDetails bookMetaDetails) {
        this.bookMetaDetails = bookMetaDetails;
    }

    public BookUserDetails getBookUserDetails() {
        return bookUserDetails;
    }

    public void setBookUserDetails(BookUserDetails bookUserDetails) {
        this.bookUserDetails = bookUserDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        bookReviewer.entity.book.Book book = (bookReviewer.entity.book.Book) o;
        return getId() == book.getId() &&
                getBookMetaDetails().getAuthor().equals(book.getBookMetaDetails().getAuthor()) &&
                getBookMetaDetails().getTitle().equals(book.getBookMetaDetails().getTitle()) &&
                Objects.equals(getBookMetaDetails().getPublishingYear(), book.getBookMetaDetails().getPublishingYear());
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                "isbn=" + isbn +
                ", bookMetaDetails=" + bookMetaDetails.toString() +
                ", bookUserDetails=" + bookUserDetails.toString() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getBookMetaDetails().getAuthor(),
                getBookMetaDetails().getTitle(), getBookMetaDetails().getPublishingYear(),
                getBookUserDetails().getContent());
    }
}
