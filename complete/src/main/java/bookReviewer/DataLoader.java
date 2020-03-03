package bookReviewer;

import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private BookRepository bookRepository;

    @Autowired
    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void run(ApplicationArguments args) {

        Book book = new Book();
        book.setAuthor("John R. R. Tolkien");
        book.setTitle("Der Herr der Ringe - Die Gefährten");
        book.setPages(608);
        book.setIsbn("978-3608939811");
        book.setPublishingYear(1954);
        book.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        bookRepository.save(book);

        Book book2 = new Book();
        book2.setAuthor("John R. R. Tolkien");
        book2.setTitle("Der Herr der Ringe - Die zwei Türme");
        book2.setPages(645);
        book2.setIsbn("978-3608933455");
        book2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        book2.setPublishingYear(1955);
        bookRepository.save(book2);

        book = new Book();
        book.setAuthor("John R. R. Tolkien");
        book.setTitle("Der Herr der Ringe - Die Rückkehr des Königs");
        book.setPages(701);
        book.setIsbn("978-3608933456");
        bookRepository.save(book);




    }
}