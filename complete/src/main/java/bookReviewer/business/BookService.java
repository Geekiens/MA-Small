package hello.business;

import hello.persistence.model.Book;
import hello.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getBooks() {
        //Book[] ps = restTemplate.getForEntity(productsURL, Product[].class).getBody();
        //return Arrays.asList(ps);
        ArrayList<Book> books = new ArrayList<Book>();
        return bookRepository.findAll();
    }

    public Book getBook(long id) {
        return bookRepository.findById(id).orElse(null);
    }


    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }



}