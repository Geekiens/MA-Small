package bookReviewer.persistence;


import bookReviewer.persistence.model.CachedOfferHistoryPersistence;
import bookReviewer.business.shared.MediaType;
import bookReviewer.persistence.model.OfferPersistence;
import bookReviewer.persistence.repository.CachedOfferHistoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CachedOfferHistoryRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CachedOfferHistoryRepository cachedOfferHistoryRepository;




}
