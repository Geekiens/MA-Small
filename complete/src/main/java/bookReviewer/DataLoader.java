package bookReviewer;

import bookReviewer.application.boundary.in.useCase.command.RegisterUserUseCase;
import bookReviewer.application.useCase.command.registerUserUseCase.RegisterUserCommand;
import bookReviewer.application.useCase.command.registerUserUseCase.RegisterUserService;
import bookReviewer.adapter.out.persistence.model.Book;
import bookReviewer.adapter.out.persistence.model.Rating;
import bookReviewer.application.shared.model.Role;
import bookReviewer.adapter.out.persistence.repository.BookRepository;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;
import bookReviewer.periphery.persistence.service.BookRepositoryService;
import bookReviewer.periphery.persistence.service.RatingRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private BookRepository bookRepository;

    private RatingRepository ratingRepository;

    private RegisterUserUseCase registerUserUseCase;

    @Autowired
    public DataLoader(BookRepository bookRepository, RegisterUserUseCase registerUserService, RatingRepository ratingRepository) {
        this.bookRepository = bookRepository;
        this.registerUserUseCase = registerUserService;
        this.ratingRepository = ratingRepository;
    }

    public void run(ApplicationArguments args) {
        try {
            // Create books
            Book book = new Book();
            book.setAuthor("John R. R. Tolkien");
            book.setTitle("Der Herr der Ringe - Die Gefährten");
            book.setPages(608);
            book.setIsbn("978-3608939811");
            book.setPublishingYear(1954);
            book.setGenre("Fantasie");
            book.setPublisher("Houghton Mifflin");
            String[] keywords = {"Zauberer", "Zwerge", "Orks", "Drachen", "mystisch", "episch"};
            book.setKeywords(keywords);
            String[] languages = {"Deutsch", "Englisch", "Französisch", "Spanisch"};
            book.setLanguages(languages);
            book.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            bookRepository.saveAndFlush(book);

            Book book2 = new Book();
            book2.setAuthor("John R. R. Tolkien");
            book2.setTitle("Der Herr der Ringe - Die zwei Türme");
            book2.setPages(645);
            book2.setIsbn("978-3608933455");
            book2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            book2.setPublishingYear(1955);
            bookRepository.saveAndFlush(book2);

            book = new Book();
            book.setAuthor("John R. R. Tolkien");
            book.setTitle("Der Herr der Ringe - Die Rückkehr des Königs");
            book.setPages(701);
            book.setIsbn("978-3608933456");
            bookRepository.saveAndFlush(book);

            // Create Users
            RegisterUserCommand registerUserCommand = new RegisterUserCommand(
                    "User",
                    "passwort",
                    "2f0df9b8-b645-4fc3-91c2-407d50706302@mailslurp.com",
                    Role.USER);
            registerUserUseCase.registerUser(registerUserCommand);
            registerUserCommand = new RegisterUserCommand(
                    "Moderator",
                    "passwort",
                    "20fd08d4-5eaf-445c-a47e-7d3055009337@mailslurp.com",
                    Role.MODERATOR);
            registerUserUseCase.registerUser(registerUserCommand);
            registerUserCommand = new RegisterUserCommand(
                    "Admin",
                    "passwort",
                    "max.master.thesis2+user@gmail.com",
                    Role.ADMIN);
            registerUserUseCase.registerUser(registerUserCommand);

            // Create Ratings
            Book ratedBook = bookRepository.findById(1L).orElse(null);
            Rating rating = new Rating(5, "Ok", 1L, "Content", ratedBook);
            Rating rating2 = new Rating(4, "Title", 2L, null, ratedBook);

            ratingRepository.saveAndFlush(rating);
            ratingRepository.saveAndFlush(rating2);





        } catch (Exception e) {
            System.out.println("Init not completed");
            e.printStackTrace();
        }




    }

}


