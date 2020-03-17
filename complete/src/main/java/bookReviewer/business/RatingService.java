package bookReviewer.business;

import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.business.model.RatingSummary;
import bookReviewer.persistence.model.*;
import bookReviewer.persistence.repository.ActivityRepository;
import bookReviewer.persistence.repository.BookRepository;
import bookReviewer.persistence.repository.RatingRepository;
import bookReviewer.persistence.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;


    final String emailSender = "max.master.thesis2@gmail.com";
    final String password = "supersafepassword";

    public List<Rating> getRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings;
    }

    public List<Rating> findRatingsById(Long bookId) {
        List<Rating> ratings = ratingRepository.findAllByBookId(bookId);

        for (int i = 0; i < ratings.size(); i++) {
            User user = userRepository.findById(ratings.get(i).getUserId()).orElse(null);
            if (user != null) {
                ratings.get(i).setAuthor(user.getUsername());
            }
        }
        return ratings;
    }

    public RatingSummary getAverageRating(Long bookId) {
        List<Rating> ratings = ratingRepository.findAllByBookId(bookId);
        RatingSummary ratingSummary = new RatingSummary();
        int sumOfRatings = 0;
        for (Rating rating : ratings) {
            sumOfRatings += rating.getStars();
            ratingSummary.addTotalVotes();
        }
        ratingSummary.setAverageRating(sumOfRatings * 1.0 / ratingSummary.getTotalVotes());
        return  ratingSummary;
    }

    public List<Rating> getRatingsByIdWithContent(Long bookId) {
        List<Rating> ratings = ratingRepository.findAllByBookIdAndContentNotNull(bookId);
        return ratings;
    }

    public Rating getRating(long id) {
        return  ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("rating not found with id " + id));
    }

    private void sendEmptyRatingEmail(User receiver, String text) {
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

    public void createRating(Long bookId, Rating rating, String token) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + bookId));
        Claims claims = JwtProvider.decodeJWT(token);
        long reviewer =((long) (int) claims.get("userId"));
        System.out.println(reviewer);
        rating.setBook(book);
        rating.setUserId(reviewer);
        User user = userRepository.findById(reviewer).orElseThrow(() -> new ResourceNotFoundException("user not found with id " + reviewer));
        System.out.println("user: " + user.getEmail());
        Activity activity = new Activity(new Date(), ActivityType.RATING_CREATED_WITH_COMMENT, user);

        if ((rating.getContent() == null || rating.getContent() == "")) {
            activity.setActivityType(ActivityType.RATING_CREATED);
            String text;
            if (rating.getStars() < 3) {
                text = "\nSchade, dass dir das Buch nicht gefallen hat. Was genau hat dich an dem Buch gestört?";
            }
            else {
                text = "\nWas hat dir besonders gut gefallen? Kennst du vielleicht andere Bücher die Lesern dieses Buches gefallen könnten?";

            }
            new Thread(() -> {
                sendEmptyRatingEmail(user, text);
            }).start();
        }

        ratingRepository.save(rating);
        activityRepository.save(activity);
    }

    public void updateRating(long bookId, Rating rating) {
        Book book = bookRepository.findById(bookId).orElse(null);
        rating.setBook(book);

        System.out.println("rating: " + rating.toString());

        ratingRepository.save(rating);
    }

    public void deleteRating(long id) {
        ratingRepository.deleteById(id);
    }



}


