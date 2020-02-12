package bookReviewer.persistence.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "keywords")
    private String[] keywords;

    @Column(name = "languages")
    private String[] languages;

    @Column(name = "publisher")
    private  String publisher;


    @Column(name = "pages")
    private int pages;


    @Column(name = "isbn")
    private String isbn;

    @Column(name = "publishingYear")
    private int publishingYear;

    @Column(name = "content")
    private String content;

    public Book() {}

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Book(String author, String title, String isbn, int publishingYear, String content) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.publishingYear = publishingYear;
        this.content = content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getId() == book.getId() &&
                getAuthor().equals(book.getAuthor()) &&
                getTitle().equals(book.getTitle()) &&
                Objects.equals(getPublishingYear(), book.getPublishingYear()) &&
                Objects.equals(getContent(), book.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getTitle(), getPublishingYear(), getContent());
    }
}

