package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.OfferHistoryMapper;
import bookReviewer.business.boundary.out.persistence.SaveOfferHistory;
import bookReviewer.entity.offerHistory.OfferHistroy;
import bookReviewer.adapter.out.persistence.model.Book;
import bookReviewer.adapter.out.persistence.repository.BookRepository;
import bookReviewer.adapter.out.persistence.repository.OfferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SaveOfferHistoryService")
public class SaveOfferHistoryService implements SaveOfferHistory {
    @Autowired
    @Qualifier("OfferHistoryRepositoryService")
    OfferHistoryRepository offerHistoryRepository;

    @Autowired
    @Qualifier("BookRepositoryService")
    BookRepository bookRepository;

    public void saveOfferHistory(OfferHistroy offerHistroy){
        Book book = bookRepository.findById(offerHistroy.getBookId()).orElse(null);
        String isbn = null;
        if (book != null){
            isbn = book.getIsbn();
        }
        offerHistoryRepository.save(OfferHistoryMapper.map(offerHistroy, isbn));
    }
}
