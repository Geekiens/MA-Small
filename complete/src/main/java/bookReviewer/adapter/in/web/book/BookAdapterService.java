package bookReviewer.adapter.in.web.book;

import bookReviewer.adapter.in.web.util.MapWithReflection;
import bookReviewer.adapter.in.web.util.token.TokenDecoder;
import bookReviewer.application.boundary.in.useCase.command.CreateBookUseCase;
import bookReviewer.application.boundary.in.useCase.command.DeleteBookUseCase;
import bookReviewer.application.boundary.in.useCase.query.GetBookUseCase;
import bookReviewer.application.boundary.in.useCase.query.GetBooksUseCase;
import bookReviewer.application.boundary.in.useCase.query.GetOffersOfBookUseCase;
import bookReviewer.application.useCase.command.deleteBookUseCase.DeleteBookCommand;
import bookReviewer.application.useCase.query.getBookUseCase.GetBookOutput;
import bookReviewer.application.useCase.query.getBooksUseCase.GetBooksOutput;
import bookReviewer.application.useCase.query.getOffersOfBookUseCase.OfferOutput;
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

    @Autowired
    TokenDecoder tokenDecoder;

    @Autowired
    CreateBookCommandMapper createBookCommandMapper;

    public Long createBook(NewBook newBook, String token){
         return createBookUseCase.createBook(createBookCommandMapper.map(token, newBook));
    }

    public void deleteBook(Long bookId, String token){
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand();
        deleteBookCommand.setBookId(bookId);
        deleteBookCommand.setRole(tokenDecoder.getRole(token));
        deleteBookUseCase.deleteBook(deleteBookCommand);
    }

    public BookWithOffers getBook(Long bookId){
        GetBookOutput book = getBookUseCase.getBook(bookId);
        List<OfferOutput> offers = getOffersOfBookUseCase.getOffers(bookId);
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
