package bookReviewer;

import bookReviewer.business.BookService;
import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @Autowired
    BookRepository bookRepository;

    public static void main(String[] args) {

        BookService bookService = new BookService();
        SpringApplication.run(Application.class, args);



    }
}
