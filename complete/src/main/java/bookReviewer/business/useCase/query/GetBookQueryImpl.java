package bookReviewer.business.useCase.query;

import bookReviewer.business.boundary.in.useCase.query.GetBookQuery;
import bookReviewer.business.model.Book;
import org.springframework.stereotype.Service;

@Service
public class GetBookQueryImpl implements GetBookQuery {
    public Book getBook(long id) {
        return null;
    }
}
