package bookReviewer.entity.book;

public class Book {
    private long id;
    private BookMetaDetails bookMetaDetails;
    private  BookUserDetails bookUserDetails;

    public Book() {
    }

    public Book(long id, BookMetaDetails bookMetaDetails, BookUserDetails bookUserDetails) {
        this.id = id;
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
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookMetaDetails=" + bookMetaDetails.toString() +
                ", bookUserDetails=" + bookUserDetails.toString() +
                '}';
    }
}
