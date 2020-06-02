package bookReviewer.business.useCase.command.createRatingUseCase;

import bookReviewer.business.boundary.in.useCase.command.CreateRatingUseCase;
import bookReviewer.business.boundary.out.emailProvider.SendEmailProvider;
import bookReviewer.business.boundary.out.persistence.*;
import bookReviewer.business.shared.exception.DuplicateRatingException;
import bookReviewer.business.shared.exception.ResourceNotFoundException;
import bookReviewer.business.shared.authorizer.CheckRole;
import bookReviewer.entity.book.Book;
import bookReviewer.entity.rating.Rating;
import bookReviewer.entity.user.Role;
import bookReviewer.entity.user.SubmissionsDate;
import bookReviewer.entity.user.Activity;
import bookReviewer.entity.user.ActivityType;
import bookReviewer.entity.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Qualifier("CreateRatingService")
public class CreateRatingService implements CreateRatingUseCase {

    @Autowired
    @Qualifier("SaveRatingService")
    SaveRating saveRating;

    @Autowired
    @Qualifier("FindAllRatingsByBookIdAndUserIdService")
    FindAllRatingsByBookIdAndUserId findAllRatingsByBookIdAndUserId;

    @Autowired
    @Qualifier("FindBookByIdService")
    FindBookById findBookById;

    @Autowired
    @Qualifier("FindUserByIdService")
    FindUserById findUserById;

    @Autowired
    @Qualifier("SaveActivityService")
    SaveActivity saveActivity;

    @Autowired
    @Qualifier("SendEmailProviderService")
    SendEmailProvider sendEmailProvider;

    public Long createRating(CreateRatingCommand createRatingCommand) {
        Book book = findBookById.findBookById(createRatingCommand.getBookId()).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + createRatingCommand.getBookId()));
        long reviewer = createRatingCommand.getUserId();
        User user = findUserById.findUserById(reviewer).orElseThrow(() -> new ResourceNotFoundException("user not found with id " + reviewer));
        Rating rating = RatingEntityMapper.map(createRatingCommand.getRating(), book.getId(), reviewer);

        CheckRole.checkHasMinimumRequiredRole(user.getRole(), Role.USER);

        System.out.println("user: " + user.getEmail());
        if (isDuplicate(rating)){
            throw new DuplicateRatingException();
        }
        SubmissionsDate submissionsDate = new SubmissionsDate(new Date());
        Activity activity = new Activity(ActivityType.RATING_CREATED_WITH_COMMENT, submissionsDate);
        if ((rating.getRatingDetails().getContent() == null || rating.getRatingDetails().getContent() == "")) {
            activity.setActivityType(ActivityType.RATING_CREATED);
            sendEmailForEmptyContent(user, rating);
        }
        saveActivity.saveActivity(activity, user.getId());
        return saveRating.saveRating(rating);
    }

    private void sendEmailForEmptyContent(User user, Rating rating) {
        String text;
        String subject = "Schreib einen Kommentar zu deiner Bewertung";
        if (rating.getRatingDetails().getScore() < 3) {
            String variableText = "\nSchade, dass dir das Buch nicht gefallen hat. Was genau hat dich an dem Buch gestört?";
            text = generateEmailTextBody(variableText, user.getCredentials().getUsername());
        }
        else {
            String variableText = "\nWas hat dir besonders gut gefallen? Kennst du vielleicht andere Bücher die Lesern dieses Buches gefallen könnten?";
            text = generateEmailTextBody(variableText, user.getCredentials().getUsername());

        }
        new Thread(() -> {
            sendEmailProvider.sendEmail(user.getEmail(), text, subject);
        }).start();
    }

    private String generateEmailTextBody(String variablePart, String username){
        return "Hallo " + username + ","
                + "\n\n danke für deine Bewertung! Mach deine Bewertung noch wertvoller, indem du einen Kommentar schreibst."
                + variablePart
                + "\n Teile jetzt deine Meinung mit Anderen!"
                + "\n\n Viele Grüße"
                + "\n dein Book-Reviewer Team";
    }

    private boolean isDuplicate(Rating rating){
        return findAllRatingsByBookIdAndUserId.findAllRatingsByBookIdAndUserId(rating.getBookId(), rating.getUserId()).size() >= 1;
    }
}
