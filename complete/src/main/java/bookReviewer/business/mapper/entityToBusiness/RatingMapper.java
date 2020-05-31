package bookReviewer.business.mapper.entityToBusiness;

import bookReviewer.business.boundary.out.persistence.FindBookById;
import bookReviewer.business.boundary.out.persistence.FindUserById;
import bookReviewer.business.model.Book;
import bookReviewer.business.model.BookBusiness;
import bookReviewer.business.model.RatingBusiness;
import bookReviewer.entity.rating.Rating;
import bookReviewer.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingMapper {
    @Autowired
    @Qualifier("FindBookByIdService")
    FindBookById findBookById;

    @Autowired
    @Qualifier("FindUserByIdService")
    FindUserById findUserById;

    public RatingBusiness map(Rating rating){
        RatingBusiness ratingBusiness = new RatingBusiness();
        ratingBusiness.setContent(rating.getRatingDetails().getContent());
        ratingBusiness.setTitle(rating.getRatingDetails().getTitle());
        ratingBusiness.setId(rating.getId());
        ratingBusiness.setScore(rating.getRatingDetails().getScore());
        ratingBusiness.setUserId(rating.getUserId());
        ratingBusiness.setBook(getBook(rating.getBookId()));
        ratingBusiness.setAuthor(getAuthor(rating.getUserId()));
        return ratingBusiness;
    }

    public List<RatingBusiness> mapList(List<Rating> ratingList){
        List<RatingBusiness> ratingBusinessList = new ArrayList<>();
        for(Rating rating: ratingList) {
            ratingBusinessList.add(map(rating));
        }
        return  ratingBusinessList;
    }


    /* TODO: Zweck in der Arbeit erklären:
        Problem Datenmodelle halten unterschiedeliche Daten
        Author ist kein Attribut der Klasse Rating (entity)
        Da der UseCase aber erwartet, dass Author vorhanden ist wird hier ein das Attribut abgefragt
        Sobald das Modell der Geschäftslogikschicht abgelöst wird, werden auch diese Mapper abgelöst
        Falls das Attirbut dann in einem Use Case fehlt muss die hier implementierte Logik übernommen werden
        Falls das jeweilige Attribut nicht benötigt wird, kann die Logik entfernt werden
        Problem dann können die Klassen nicht final (static) sein, da die Implementierungen der Persistenzinterfaces benutzt werden
        Theoretisch wären dadurch kreisförmige möglich
        Die Datenmodelle selber sind zwar kreisfrei, aber da zwei Modelle verwendet werden, könnten sich Kreise ergeben
        z.B. wenn eine Beziehung invertiert wurde
        Durch die Implementierung der Mapper entity -> business und business -> entity wird diese Problem gelöst

        In MA-Fazit: Komplexität wird hauptsächlich durch die Differenz zwischen den Modellen getrieben
     */

    private BookBusiness getBook(Long bookId){
        return BookMapper.map(findBookById.findBookById(bookId).orElse(null));
    }

    private String getAuthor(Long userId){
        User user = findUserById.findUserById(userId).orElse(null);
        return user.getCredentials().getUsername();
    }


}

