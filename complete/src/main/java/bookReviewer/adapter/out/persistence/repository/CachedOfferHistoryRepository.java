package bookReviewer.adapter.out.persistence.repository;

import bookReviewer.adapter.out.persistence.model.CachedOfferHistory;
import bookReviewer.adapter.out.persistence.model.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CachedOfferHistoryRepository extends JpaRepository<CachedOfferHistory, Long> {

    //@Query("select c from CachedOfferHistory c " +
    //        "where c.isbn = :isbn")
    List<CachedOfferHistory> findByIsbn(
                    @Param("isbn") String isbn);

    @Query("select c from CachedOfferHistory c " +
            "where c.isbn = :isbn " +
            "and c.vendor = :vendor " +
            "and c.mediaType = :mediaType")
    CachedOfferHistory findByIsbnAndVendorAndMediaType(
            @Param("isbn") String isbn,
            @Param("vendor") String vendor,
            @Param("mediaType") MediaType mediaType);


}
