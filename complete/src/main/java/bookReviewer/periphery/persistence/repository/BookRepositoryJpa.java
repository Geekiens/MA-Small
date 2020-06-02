package bookReviewer.adapter.out.persistence.repository;

import bookReviewer.adapter.out.persistence.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


}