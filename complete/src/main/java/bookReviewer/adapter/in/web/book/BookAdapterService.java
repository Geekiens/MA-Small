package bookReviewer.adapter.in.web.book;

import bookReviewer.adapter.in.web.util.MapWithReflection;
import bookReviewer.adapter.in.web.util.TokenDecoder;
import bookReviewer.business.boundary.in.useCase.command.CreateBookUseCase;
import bookReviewer.business.boundary.in.useCase.command.DeleteBookUseCase;
import bookReviewer.business.boundary.in.useCase.query.GetBookUseCase;
import bookReviewer.business.boundary.in.useCase.query.GetBooksUseCase;
import bookReviewer.business.boundary.in.useCase.query.GetOffersOfBookUseCase;
import bookReviewer.business.useCase.command.deleteBookUseCase.DeleteBookCommand;
import bookReviewer.business.useCase.query.getBookUseCase.GetBookOutput;
import bookReviewer.business.useCase.query.getBooksUseCase.GetBooksOutput;
import bookReviewer.business.useCase.query.getOffersOfBookUseCase.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("BookAdapterService")
public class BookAdapterService implements BookAdapter{

    @Autowired
    @Qualifier("CreateBookService")
    CreateBookUseCase createBookUseCase;

    @Autowired
    @Qualifier("DeleteBookService")
    DeleteBookUseCase deleteBookUseCase;

    @Autowired
    @Qualifier("GetBookService")
    GetBookUseCase getBookUseCase;

    @Autowired
    @Qualifier("GetOffersOfBookService")
    GetOffersOfBookUseCase getOffersOfBookUseCase;

    @Autowired
    @Qualifier("GetBooksService")
    GetBooksUseCase getBooksUseCase;

    public Long createBook(NewBook newBook, String token){
         return createBookUseCase.createBook(CreateBookCommandMapper.map(token, newBook));
    }

    public void deleteBook(Long bookId, String token){
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand();
        deleteBookCommand.setBookId(bookId);
        deleteBookCommand.setRole(TokenDecoder.getRole(token));
        deleteBookUseCase.deleteBook(deleteBookCommand);
    }

    public BookWithOffers getBook(Long bookId){
        GetBookOutput book = getBookUseCase.getBook(bookId);
        List<Offer> offers = getOffersOfBookUseCase.getOffers(bookId);
        return BookWithOffersMapper.map(book, offers);
    }

    public List<BookWithRatingInformation> getBooks(){
        List<GetBooksOutput> books = getBooksUseCase.getBooks();
        List<BookWithRatingInformation> bookWithRatingList = new ArrayList<>();
        for (GetBooksOutput book : books){
            BookWithRatingInformation bookWithRatingInformation = new BookWithRatingInformation();
            try{
                MapWithReflection.copy(book, bookWithRatingInformation);
                bookWithRatingList.add(bookWithRatingInformation);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }
        return bookWithRatingList;
    }
}
