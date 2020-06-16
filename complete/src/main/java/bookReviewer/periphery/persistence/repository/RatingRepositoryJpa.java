package bookReviewer.periphery.persistence.repository;

import bookReviewer.adapter.out.persistence.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RatingRepositoryJpa extends JpaRepository<Rating, Long> {

    List<Rating> findAllByBookId(Long bookId);

    List<Rating> findAllByBookIdAndUserId(Long bookId, Long userId);

    List<Rating> findAllByBookIdAndContentNotNull(Long bookId);
}
