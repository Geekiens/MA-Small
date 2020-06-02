package bookReviewer.periphery.persistence.repository;

import bookReviewer.adapter.out.persistence.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepositoryJpa extends JpaRepository<Book, Long> {


}