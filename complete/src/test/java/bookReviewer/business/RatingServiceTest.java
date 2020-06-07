package bookReviewer.business;

//import bookReviewer.business.util.JwtProvider;
import bookReviewer.business.model.RatingSummary;
import bookReviewer.business.service.RatingService;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.model.Role;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.RatingRepository;
import bookReviewer.persistence.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.Transport;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class RatingServiceTest {

    @Mock
    RatingRepository ratingRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    Transport transport;

    //@Mock
    //JwtProvider jwtProvider;

    @InjectMocks
    RatingService ratingService;

    @Before
    public void setup() {

    }

    // TODO: Test , findRatingsById, createRating

}
