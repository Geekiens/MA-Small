package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.OfferHistoryMapper;
import bookReviewer.application.boundary.out.persistence.SaveOfferHistory;
import bookReviewer.entity.offerHistory.OfferHistroy;
import bookReviewer.adapter.out.persistence.model.Book;
import bookReviewer.adapter.out.persistence.repository.BookRepository;
import bookReviewer.adapter.out.persistence.repository.OfferHistoryRepository;

public class SaveOfferHistoryService implements SaveOfferHistory {

    BookRepository bookRepository;

    OfferHistoryRepository offerHistoryRepository;

    public SaveOfferHistoryService(BookRepository bookRepository, OfferHistoryRepository offerHistoryRepository){
        this.bookRepository = bookRepository;
        this.offerHistoryRepository = offerHistoryRepository;
    }

    public void saveOfferHistory(OfferHistroy offerHistroy){
        Book book = bookRepository.findById(offerHistroy.getBookId()).orElse(null);
        String isbn = null;
        if (book != null){
            isbn = book.getIsbn();
        }
        offerHistoryRepository.save(OfferHistoryMapper.map(offerHistroy, isbn));
    }
}
