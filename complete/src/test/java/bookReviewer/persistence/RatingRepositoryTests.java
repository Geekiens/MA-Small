package bookReviewer.persistence;

import bookReviewer.adapter.out.persistence.model.Book;
import bookReviewer.adapter.out.persistence.model.Rating;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RatingRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RatingRepository ratingRepository;

    Book book = new Book();


    @Before
    public void setup(){
        entityManager.clear();
        System.out.println("Before Each");
        book = entityManager.persistFlushFind(book);
        Rating rating = new Rating(4, "Title", 1L, "content", book);
        Rating rating2 = new Rating(4, "Title", 2L, null, book);
        entityManager.persist(rating);
        entityManager.persist(rating2);
        entityManager.flush();

    }

    @Test
    public void WhenFindAllByBookId_thenReturnAllRatingsForBook() {
        // Given
        long id = (long) entityManager.getId(book);

        // When
        List<Rating> ratings =  ratingRepository.findAllByBookId(id);

        // Then
        Assert.assertEquals(2, ratings.size());
    }

    @Test
    public void whenFindAllByBookIdAndContentNotNull_thenReturnAllRatingForBookWithComment() {
        // Given
        long id = (long) entityManager.getId(book);

        // When
        List<Rating> ratings =  ratingRepository.findAllByBookIdAndContentNotNull(id);

        // Then
        Assert.assertEquals(1, ratings.size());
        Assert.assertEquals("content", ratings.get(0).getContent());
    }

}
