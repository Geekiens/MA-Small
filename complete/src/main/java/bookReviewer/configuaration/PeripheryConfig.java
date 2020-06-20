package bookReviewer.configuaration;

import bookReviewer.adapter.in.scheduler.ScheduleAdapter;
import bookReviewer.adapter.in.scheduler.ScheduleAdapterService;
import bookReviewer.adapter.in.web.book.BookAdapter;
import bookReviewer.adapter.in.web.book.BookAdapterService;
import bookReviewer.adapter.in.web.book.CreateBookCommandMapper;
import bookReviewer.adapter.in.web.rating.*;
import bookReviewer.adapter.in.web.user.UserAdapter;
import bookReviewer.adapter.in.web.user.UserAdapterService;
import bookReviewer.adapter.in.web.util.token.JwtAdapter;
import bookReviewer.adapter.in.web.util.token.JwtProvider;
import bookReviewer.adapter.in.web.util.token.TokenDecoder;
import bookReviewer.adapter.out.emailProvider.EmailAdapter;
import bookReviewer.adapter.out.emailProvider.SendEmailProviderService;
import bookReviewer.adapter.out.externalSystems.buchLaden123.Buchladen123Adapter;
import bookReviewer.adapter.out.externalSystems.buchLaden123.ReceiveOffersOfBuchladen123Service;
import bookReviewer.adapter.out.externalSystems.buchVerkauf24.BuchVerkauf24Adapter;
import bookReviewer.adapter.out.externalSystems.buchVerkauf24.ReceiveOffersOfBuchVerkauf24Service;
import bookReviewer.adapter.out.externalSystems.currencyApi.CurrencyAdapter;
import bookReviewer.adapter.out.externalSystems.currencyApi.CurrencyApi;
import bookReviewer.adapter.out.externalSystems.yourFavoriteBookVendor.ReceiveOffersOfYourFavoriteBookVendorService;
import bookReviewer.adapter.out.externalSystems.yourFavoriteBookVendor.YourFavoriteBookVendorAdapter;
import bookReviewer.adapter.out.persistence.repository.*;
import bookReviewer.adapter.out.persistence.service.*;
import bookReviewer.application.boundary.in.useCase.command.*;
import bookReviewer.application.boundary.in.useCase.query.*;
import bookReviewer.application.boundary.out.emailProvider.SendEmailProvider;
import bookReviewer.application.boundary.out.externalSystems.ReceiveOffersOfBuchVerkauf24;
import bookReviewer.application.boundary.out.externalSystems.ReceiveOffersOfBuchladen123;
import bookReviewer.application.boundary.out.externalSystems.ReceiveOffersOfYourFavoriteBookVendor;
import bookReviewer.application.boundary.out.persistence.*;
import bookReviewer.application.useCase.command.checkUserPromotionUseCase.CheckUserPromotionService;
import bookReviewer.application.useCase.command.createBookUseCase.CreateBookService;
import bookReviewer.application.useCase.command.createRatingUseCase.CreateRatingService;
import bookReviewer.application.useCase.command.deleteBookUseCase.DeleteBookService;
import bookReviewer.application.useCase.command.deleteRatingUseCase.DeleteRatingService;
import bookReviewer.application.useCase.command.registerUserUseCase.RegisterUserService;
import bookReviewer.application.useCase.command.setFavoriteBookUseCase.SetFavoriteBookService;
import bookReviewer.application.useCase.command.updateRatingUseCase.UpdateRatingService;
import bookReviewer.application.useCase.query.getBookUseCase.GetBookService;
import bookReviewer.application.useCase.query.getBooksUseCase.GetBooksService;
import bookReviewer.application.useCase.query.getOffersOfBookUseCase.GetOffersOfBookService;
import bookReviewer.application.useCase.query.getRatingsOfBookUseCase.GetRatingsOfBookService;
import bookReviewer.application.useCase.query.getRatingsOfBookWithContentUseCase.GetRatingsOfBookWithContentService;
import bookReviewer.application.useCase.query.getTokenByLoginUseCase.GetTokenByLoginService;
import bookReviewer.periphery.emailProvider.EmailAdapterService;
import bookReviewer.periphery.externalSystems.Buchladen123AdapterService;
import bookReviewer.periphery.externalSystems.Buchverkauf24AdapterService;
import bookReviewer.periphery.externalSystems.CurrencyAdapterService;
import bookReviewer.periphery.externalSystems.YourFavoriteBookVendorAdapterService;
import bookReviewer.periphery.libaries.JwtAdapterService;
import bookReviewer.periphery.persistence.repository.*;
import bookReviewer.periphery.persistence.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

@Configuration
@EntityScan(basePackages = "bookReviewer.adapter.out.persistence.model")
@ComponentScan(basePackages = "bookReviewer") // excludeFilters
@EnableJpaRepositories(basePackages = {
        "bookReviewer.periphery.persistence.repository"
})

public class PeripheryConfig {

    @Autowired
    private ApplicationContext appContext;


    @Bean("ActivityRepositoryService")
    public ActivityRepository activityRepository() {
        return new ActivityRepositoryService(appContext.getBean(ActivityRepositoryJpa.class));
    }

    @Bean("BookRepositoryService")
    public BookRepository bookRepository() {
        return new BookRepositoryService(appContext.getBean(BookRepositoryJpa.class));
    }

    @Bean("OfferHistoryRepositoryService")
    public OfferHistoryRepository offerHistoryRepository() {
        return new OfferHistoryRepositoryService(appContext.getBean(OfferHistoryRepositoryJpa.class));
    }

    @Bean("RatingRepositoryService")
    public RatingRepository ratingRepository() {
        return new RatingRepositoryService(appContext.getBean(RatingRepositoryJpa.class));
    }

    @Bean("UserRepositoryService")
    public UserRepository userRepository() {
        return new UserRepositoryService(appContext.getBean(UserRepositoryJpa.class));
    }

    @Bean("FindAllActivitiesByUserService")
    public FindAllActivitiesByUser findAllActivitiesByUser() {
        return new FindAllActivitiesByUserService(appContext.getBean(ActivityRepository.class));
    }

    @Bean("FindAllUsersService")
    public FindAllUsers findAllUsers() {
        return new FindAllUsersService(appContext.getBean(ActivityRepository.class), appContext.getBean(UserRepository.class));
    }

    @Bean("FindUserByIdService")
    public FindUserById findUserById() {
        return new FindUserByIdService(appContext.getBean(ActivityRepository.class), appContext.getBean(UserRepository.class));
    }

    @Bean("FindUserByUsernameService")
    public FindUserByUsername findUserByUsername() {
        return new FindUserByUsernameService(appContext.getBean(ActivityRepository.class), appContext.getBean(UserRepository.class));
    }

    @Bean("SaveActivityService")
    public SaveActivity saveActivity() {
        return new SaveActivityService(appContext.getBean(ActivityRepository.class), appContext.getBean(UserRepository.class));
    }

    @Bean("DeleteBookByIdService")
    public DeleteBookById deleteBookById() {
        return new DeleteBookByIdService(appContext.getBean(BookRepository.class));
    }

    @Bean("FindAllBooksService")
    public FindAllBooks findAllBooks() {
        return new FindAllBooksService(appContext.getBean(BookRepository.class));
    }

    @Bean("FindBookByIdService")
    public FindBookById findBookById() {
        return new FindBookByIdService(appContext.getBean(BookRepository.class));
    }

    @Bean("SaveBookService")
    public SaveBook saveBook() {
        return new SaveBookService(appContext.getBean(BookRepository.class));
    }

    @Bean("SaveOfferHistoryService")
    public SaveOfferHistory saveOfferHistory() {
        return new SaveOfferHistoryService(appContext.getBean(BookRepository.class), appContext.getBean(OfferHistoryRepository.class));
    }

    @Bean("SaveRatingService")
    public SaveRating SaveRating() {
        return new SaveRatingService(appContext.getBean(BookRepository.class),
                appContext.getBean(RatingRepository.class),
                appContext.getBean(UserRepository.class));
    }


    @Bean("FindAllOfferHistoriesByIsbnService")
    public FindAllOfferHistoriesByIsbn findAllOfferHistoriesByIsbn() {
        return new FindAllOfferHistoriesByIsbnService(appContext.getBean(OfferHistoryRepository.class));
    }

    @Bean("FindOfferHistoryService")
    public FindOfferHistory findOfferHistory() {
        return new FindOfferHistoryService(appContext.getBean(OfferHistoryRepository.class));
    }

    @Bean("DeleteRatingByIdService")
    public DeleteRatingById deleteRatingById() {
        return new DeleteRatingByIdService(appContext.getBean(RatingRepository.class));
    }

    @Bean("FindAllRatingsByBookIdAndUserIdService")
    public FindAllRatingsByBookIdAndUserId findAllRatingsByBookIdAndUserId() {
        return new FindAllRatingsByBookIdAndUserIdService(appContext.getBean(RatingRepository.class));
    }

    @Bean("FindAllRatingsByBookIdService")
    public FindAllRatingsByBookId findAllRatingsByBookId() {
        return new FindAllRatingsByBookIdService(appContext.getBean(RatingRepository.class));
    }

    @Bean("FindAllRatingsByBookIdWithContentService")
    public FindAllRatingsByBookIdWithContent findAllRatingsByBookIdWithContent() {
        return new FindAllRatingsByBookIdWithContentService(appContext.getBean(RatingRepository.class));
    }

    @Bean("FindRatingByIdService")
    public FindRatingById findRatingById() {
        return new FindRatingByIdService(appContext.getBean(RatingRepository.class));
    }

    @Bean("SaveUserService")
    public SaveUser saveUser() {
        return new SaveUserService(appContext.getBean(UserRepository.class));
    }

    @Bean("JwtAdapterService")
    public JwtAdapter jwtAdapter() {
        return new JwtAdapterService();
    }

    @Bean("Buchladen123AdapterService")
    public Buchladen123Adapter buchladen123Adapter() {
        return new Buchladen123AdapterService();
    }

    @Bean("Buchverkauf24AdapterService")
    public BuchVerkauf24Adapter buchverkauf24Adapter() {
        return new Buchverkauf24AdapterService();
    }

    @Bean("CurrencyAdapterService")
    public CurrencyAdapter currencyAdapter() {
        return new CurrencyAdapterService();
    }

    @Bean("YourFavoriteBookVendorAdapterService")
    public YourFavoriteBookVendorAdapter yourFavoriteBookVendorAdapter() {
        return new YourFavoriteBookVendorAdapterService();
    }

    @Bean("EmailAdapterService")
    public EmailAdapter emailAdapter() {
        return new EmailAdapterService();
    }

    @Bean("ScheduleAdapterService")
    public ScheduleAdapter scheduleAdapter() {
        return new ScheduleAdapterService(appContext.getBean(CheckUserPromotionUseCase.class));
    }

    @Bean("TokenDecoder")
    public TokenDecoder tokenDecoder() {
        return new TokenDecoder(appContext.getBean(JwtProvider.class));
    }

    @Bean("CreateBookCommandMapper")
    public CreateBookCommandMapper createBookCommandMapper() {
        return new CreateBookCommandMapper(appContext.getBean(TokenDecoder.class));
    }

    @Bean("BookAdapterService")
    public BookAdapter bookAdapter() {
        return new BookAdapterService(appContext.getBean(CreateBookUseCase.class),
                appContext.getBean(DeleteBookUseCase.class),
                appContext.getBean(GetBookUseCase.class),
                appContext.getBean(GetBooksUseCase.class),
                appContext.getBean(GetOffersOfBookUseCase.class),
                appContext.getBean(SetFavoriteBookUseCase.class),
                appContext.getBean(TokenDecoder.class),
                appContext.getBean(CreateBookCommandMapper.class)
                );
    }

    @Bean("CreateRatingCommandMapper")
    public CreateRatingCommandMapper createRatingCommandMapper() {
        return new CreateRatingCommandMapper(appContext.getBean(TokenDecoder.class));
    }

    @Bean("DeleteRatingCommandMapper")
    public DeleteRatingCommandMapper deleteRatingCommandMapper() {
        return new DeleteRatingCommandMapper(appContext.getBean(TokenDecoder.class));
    }

    @Bean("UpdateRatingCommandMapper")
    public UpdateRatingCommandMapper updateRatingCommandMapper() {
        return new UpdateRatingCommandMapper(appContext.getBean(TokenDecoder.class));
    }

    @Bean("RatingAdapterService")
    public RatingAdapter ratingAdapter() {
        return new RatingAdapterService(appContext.getBean(CreateRatingUseCase.class),
                appContext.getBean(DeleteRatingUseCase.class),
                appContext.getBean(UpdateRatingUseCase.class),
                appContext.getBean(GetRatingsOfBookUseCase.class),
                appContext.getBean(GetRatingsOfBookWithContentUseCase.class),
                appContext.getBean(CreateRatingCommandMapper.class),
                appContext.getBean(DeleteRatingCommandMapper.class),
                appContext.getBean(UpdateRatingCommandMapper.class)
        );
    }

    @Bean("UserAdapterService")
    public UserAdapter userAdapter() {
        return new UserAdapterService(appContext.getBean(RegisterUserUseCase.class),
                appContext.getBean(GetTokenByLoginUseCase.class),
                appContext.getBean(JwtProvider.class)
        );
    }

    @Bean("JwtProvider")
    public JwtProvider jwtProvider() {
        return new JwtProvider(appContext.getBean(JwtAdapter.class)
        );
    }

    @Bean("SendEmailProviderService")
    public SendEmailProvider sendEmailProvider() {
        return new SendEmailProviderService(appContext.getBean(EmailAdapter.class)
        );
    }

    @Bean("ReceiveOffersOfBuchladen123Service")
    public ReceiveOffersOfBuchladen123 receiveOffersOfBuchladen123() {
        return new ReceiveOffersOfBuchladen123Service(appContext.getBean(Buchladen123Adapter.class)
        );
    }

    @Bean("ReceiveOffersOfBuchVerkauf24Service")
    public ReceiveOffersOfBuchVerkauf24 receiveOffersOfBuchVerkauf24() {
        return new ReceiveOffersOfBuchVerkauf24Service(appContext.getBean(BuchVerkauf24Adapter.class)
        );
    }

    @Bean("CurrencyApi")
    public CurrencyApi currencyApi() {
        return new CurrencyApi(appContext.getBean(CurrencyAdapter.class)
        );
    }

    @Bean("ReceiveOffersOfYourFavoriteBookVendorService")
    public ReceiveOffersOfYourFavoriteBookVendor receiveOffersOfYourFavoriteBookVendor() {
        return new ReceiveOffersOfYourFavoriteBookVendorService(appContext.getBean(YourFavoriteBookVendorAdapter.class),
                appContext.getBean(CurrencyApi.class)
        );
    }

    @Bean("CheckUserPromotionService")
    public CheckUserPromotionUseCase checkUserPromotionUseCase() {
        return new CheckUserPromotionService(appContext.getBean(FindAllUsers.class),
                appContext.getBean(SaveUser.class),
                appContext.getBean(FindAllActivitiesByUser.class)
        );
    }

    @Bean("CreateBookService")
    public CreateBookUseCase createBookUseCase() {
        return new CreateBookService(appContext.getBean(SaveBook.class),
                appContext.getBean(SaveActivity.class),
                appContext.getBean(FindUserById.class)
        );
    }

    @Bean("CreateRatingService")
    public CreateRatingUseCase createRatingUseCase() {
        return new CreateRatingService(appContext.getBean(SaveRating.class),
                appContext.getBean(FindAllRatingsByBookIdAndUserId.class),
                appContext.getBean(FindBookById.class),
                appContext.getBean(FindUserById.class),
                appContext.getBean(SaveActivity.class),
                appContext.getBean(SendEmailProvider.class)
        );
    }

    @Bean("DeleteBookService")
    public DeleteBookUseCase deleteBookUseCase() {
        return new DeleteBookService(appContext.getBean(FindBookById.class),
                appContext.getBean(DeleteBookById.class));
    }

    @Bean("DeleteRatingService")
    public DeleteRatingUseCase deleteRatingUseCase() {
        return new DeleteRatingService(appContext.getBean(DeleteRatingById.class));
    }

    @Bean("RegisterUserService")
    public RegisterUserUseCase registerUserUseCase() {
        return new RegisterUserService(appContext.getBean(SaveUser.class));
    }

    @Bean("UpdateRatingService")
    public UpdateRatingUseCase updateRatingUseCase() {
        return new UpdateRatingService(appContext.getBean(SaveRating.class),
                appContext.getBean(FindBookById.class),
                appContext.getBean(FindRatingById.class)
        );
    }

    @Bean("GetBooksService")
    public GetBooksUseCase getBooksUseCase() {
        return new GetBooksService(appContext.getBean(FindAllBooks.class),
                appContext.getBean(FindAllRatingsByBookId.class)
        );
    }

    @Bean("SetFavoriteBookService")
    public SetFavoriteBookUseCase setFavoriteBookUseCase() {
        return new SetFavoriteBookService(appContext.getBean(FindUserById.class),
                appContext.getBean(FindBookById.class),
                appContext.getBean(SaveUser.class),
                appContext.getBean(SaveBook.class)
        );
    }

    @Bean("GetBookService")
    public GetBookUseCase getBookUseCase() {
        return new GetBookService(appContext.getBean(FindBookById.class),
                appContext.getBean(FindAllRatingsByBookId.class)
        );
    }

    @Bean("GetOffersOfBookService")
    public GetOffersOfBookUseCase getOffersOfBookUseCase() {
        return new GetOffersOfBookService(appContext.getBean(FindBookById.class),
                appContext.getBean(FindAllOfferHistoriesByIsbn.class),
                appContext.getBean(FindOfferHistory.class),
                appContext.getBean(SaveOfferHistory.class),
                appContext.getBean(ReceiveOffersOfBuchladen123.class),
                appContext.getBean(ReceiveOffersOfBuchVerkauf24.class),
                appContext.getBean(ReceiveOffersOfYourFavoriteBookVendor.class)
        );
    }

    @Bean("GetRatingsOfBookService")
    public GetRatingsOfBookUseCase getRatingsOfBookUseCase() {
        return new GetRatingsOfBookService(appContext.getBean(FindAllRatingsByBookId.class),
                appContext.getBean(FindUserById.class)
        );
    }

    @Bean("GetRatingsOfBookWithContentService")
    public GetRatingsOfBookWithContentUseCase getRatingsOfBookWithContentUseCase() {
        return new GetRatingsOfBookWithContentService(appContext.getBean(FindAllRatingsByBookIdWithContent.class),
                appContext.getBean(FindUserById.class)
        );
    }

    @Bean("GetTokenByLoginService")
    public GetTokenByLoginUseCase getTokenByLoginUseCase() {
        return new GetTokenByLoginService(appContext.getBean(FindUserByUsername.class));
    }
}

