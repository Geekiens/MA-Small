package bookReviewer.business;

//import bookReviewer.business.util.JwtProvider;
import bookReviewer.business.service.RatingService;
import bookReviewer.persistence.repository.RatingRepository;
import bookReviewer.persistence.repository.UserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.Transport;

import static org.mockito.ArgumentMatchers.any;


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
