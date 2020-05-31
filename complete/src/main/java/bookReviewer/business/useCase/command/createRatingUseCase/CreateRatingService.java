package bookReviewer.business.useCase.command.createRatingUseCase;

import bookReviewer.business.boundary.in.useCase.command.CreateRatingUseCase;
import bookReviewer.business.boundary.out.persistence.*;
import bookReviewer.business.exception.DuplicateRatingException;
import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.business.mapper.businessToEntity.RatingMapper;
import bookReviewer.business.mapper.entityToBusiness.BookMapper;
import bookReviewer.business.mapper.entityToBusiness.UserMapper;
import bookReviewer.business.model.BookBusiness;
import bookReviewer.business.model.RatingBusiness;
import bookReviewer.business.model.UserBusiness;
import bookReviewer.business.util.JwtProvider;
import bookReviewer.entity.user.SubmissionsDate;
import bookReviewer.entity.user.Activity;
import bookReviewer.entity.user.ActivityType;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

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

    final String emailSender = "max.master.thesis2@gmail.com";
    final String password = "supersafepassword";


    public Long createRating(Long bookId, RatingBusiness rating, String token) {
        BookBusiness book = BookMapper.map(findBookById.findBookById(bookId).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + bookId)));
        Claims claims = JwtProvider.decodeJWT(token);
        long reviewer =((long) (int) claims.get("userId"));

        rating.setBook(book);
        rating.setUserId(reviewer);
        UserBusiness user = UserMapper.map(findUserById.findUserById(reviewer).orElseThrow(() -> new ResourceNotFoundException("user not found with id " + reviewer)));
        System.out.println("user: " + user.getEmail());
        if (isDuplicate(rating)){
            throw new DuplicateRatingException();
        }

        SubmissionsDate submissionsDate = new SubmissionsDate(new Date());
        Activity activity = new Activity(ActivityType.RATING_CREATED_WITH_COMMENT, submissionsDate);
        if ((rating.getContent() == null || rating.getContent() == "")) {
            activity.setActivityType(ActivityType.RATING_CREATED);
            String text;
            if (rating.getScore() < 3) {
                text = "\nSchade, dass dir das Buch nicht gefallen hat. Was genau hat dich an dem Buch gestört?";
            }
            else {
                text = "\nWas hat dir besonders gut gefallen? Kennst du vielleicht andere Bücher die Lesern dieses Buches gefallen könnten?";
            }
            new Thread(() -> {
                sendEmptyRatingEmail(user, text);
            }).start();
        }

        saveActivity.saveActivity(activity, user.getId());
        return saveRating.saveRating(RatingMapper.map(rating));
    }

    private void sendEmptyRatingEmail(UserBusiness receiver, String text) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailSender, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailSender));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiver.getEmail())
            );
            message.setSubject("Schreib einen Kommentar zu deiner Bewertung");
            message.setText("Hallo " + receiver.getUsername() + ","
                    + "\n\n danke für deine Bewertung! Mach deine Bewertung noch wertvoller, indem du einen Kommentar schreibst."
                    + text
                    + "\n Teile jetzt deine Meinung mit Anderen!"
                    + "\n\n Viele Grüße"
                    + "\n dein Book-Reviewer Team");

            System.out.println("Sending Email to: " + receiver.getEmail());
            Transport.send(message);
            System.out.println("Email sentt to: " + receiver.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private boolean isDuplicate(RatingBusiness rating){
        return findAllRatingsByBookIdAndUserId.findAllRatingsByBookIdAndUserId(rating.getBook().getId(), rating.getUserId()).size() >= 1;
    }
}
