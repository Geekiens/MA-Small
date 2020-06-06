package bookReviewer.adapter.in.web.rating;

import bookReviewer.adapter.in.web.util.token.TokenDecoder;
import bookReviewer.application.shared.model.Role;
import bookReviewer.application.useCase.command.deleteRatingUseCase.DeleteRatingCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteRatingCommandMapper {

    @Autowired
    TokenDecoder tokenDecoder;

    public DeleteRatingCommand map(Long ratingId, String token){
        DeleteRatingCommand deleteRatingCommand = new DeleteRatingCommand();
        deleteRatingCommand.setRatingId(ratingId);

        Long userId = tokenDecoder.getUserId(token);
        deleteRatingCommand.setUserId(userId);

        Role role = tokenDecoder.getRole(token);
        deleteRatingCommand.setRole(role);
        return deleteRatingCommand;
    }
}
