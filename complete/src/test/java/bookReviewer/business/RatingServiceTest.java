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

    @Test
    public void whenGetAverageRating_thenReturnAverage(){
        // given
        Rating rating = new Rating(3, "title", 1L, "content", null);
        Rating rating2 = new Rating(5, "title", 1L, "content", null);
        ArrayList<Rating> ratings = new ArrayList<>();
        ratings.add(rating);
        ratings.add(rating2);
        when(ratingRepository.findAllByBookId(1L)).thenReturn(ratings);

        // when
        RatingSummary ratingSummary = ratingService.getAverageRating(1L);

        // then
        Assert.assertEquals(2, ratingSummary.getTotalVotes());
        Assert.assertEquals(Double.valueOf(4) , ratingSummary.getAverageRating());
    }

    @Test
    public void whenFindRatingsById_thenReturnRatingWithAuthor(){
        // given
        Rating rating = new Rating(3, "title", 2L, "content", null);
        ArrayList<Rating> ratings = new ArrayList<>();
        ratings.add(rating);
        when(ratingRepository.findAllByBookId(1L)).thenReturn(ratings);
        User user = new User("username", "password", "email", null, Role.USER, 0);
        User user2 = new User("username", "password", "email", null, Role.USER, 1);

        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user2));

        // when
        List<Rating> returnedRatings = ratingService.findRatingsById(1L);

        // then
        Assert.assertEquals(1, returnedRatings.size());
        Assert.assertEquals("username", returnedRatings.get(0).getAuthor());
    }

    // @Test
    /*public void whenCreateRating_thenSendMail() throws Exception{
        // given
        Rating rating = new Rating(3, "title", 2L, "content", null);
        ArrayList<Rating> ratings = new ArrayList<>();
        ratings.add(rating);
        when(ratingRepository.findAllByBookId(1L)).thenReturn(ratings);
        User user = new User("username", "password", "email", null, Role.USER);
        User user2 = new User("username", "password", "email", null, Role.USER);

        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user2));

        HashMap claims = new HashMap();
        claims.put("userId", 1L);
        ClaimsMock claimsMock = new ClaimsMock(claims);
        when(JwtProvider.decodeJWT("token")).thenReturn(claimsMock);
        // transport = Mockito.mock(Transport.class);

        // when
        ratingService.createRating(1L, rating, "token");

        // then
        //Assert.assertEquals(1, returnedRatings.size());
        // Assert.assertEquals("username", returnedRatings.get(0).getAuthor());
    }

     */
}
