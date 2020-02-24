package bookReviewer.persistence.repository;

import bookReviewer.business.model.MediaType;
import bookReviewer.persistence.model.CachedOfferHistoryPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CachedOfferHistoryRepository extends JpaRepository<CachedOfferHistoryPersistence, Long> {

    //@Query("select c from CachedOfferHistoryPersistence c " +
    //        "where c.isbn = :isbn")
    List<CachedOfferHistoryPersistence> findByIsbn(
                    @Param("isbn") String isbn);

    @Query("select c from CachedOfferHistoryPersistence c " +
            "where c.isbn = :isbn " +
            "and c.vendor = :vendor " +
            "and c.mediaType = :mediaType")
    CachedOfferHistoryPersistence findByIsbnAndVendorAndMediaType(
            @Param("isbn") String isbn,
            @Param("vendor") String vendor,
            @Param("mediaType") MediaType mediaType);


}
