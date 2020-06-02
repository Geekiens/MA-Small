package bookReviewer.persistence;


import bookReviewer.adapter.out.persistence.repository.OfferHistoryRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class OfferHistoryRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OfferHistoryRepository offerHistoryRepository;




}
