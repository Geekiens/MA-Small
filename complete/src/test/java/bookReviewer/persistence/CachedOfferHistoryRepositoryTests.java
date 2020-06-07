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

import static bookReviewer.persistence.model.MediaType.HARDCOVER;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CachedOfferHistoryRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CachedOfferHistoryRepository cachedOfferHistoryRepository;



    @Test
    public void whenFindByIsbnAndVendorAndMediaType_thenReturnList() {
        // given
        OfferPersistence offer = new OfferPersistence(new BigDecimal(9.99), LocalDate.now());
        CachedOfferHistoryPersistence cachedOfferHistory = new CachedOfferHistoryPersistence();
        cachedOfferHistory.setMediaType(HARDCOVER);
        cachedOfferHistory.setVendor("vendor");
        cachedOfferHistory.setIsbn("isbn");
        cachedOfferHistory = entityManager.persistFlushFind(cachedOfferHistory);
        offer.setCachedOfferHistoryPersistence(cachedOfferHistory);
        entityManager.persist(offer);

        entityManager.flush();

        // when
        CachedOfferHistoryPersistence result = cachedOfferHistoryRepository.findByIsbnAndVendorAndMediaType("isbn", "vendor", HARDCOVER);

        // then
        Assert.assertEquals(1, result.getOffers().size());
        Assert.assertEquals("vendor", result.getVendor());
        Assert.assertEquals("isbn", result.getIsbn());
        Assert.assertEquals(MediaType.HARDCOVER, result.getMediaType());
    }




}
