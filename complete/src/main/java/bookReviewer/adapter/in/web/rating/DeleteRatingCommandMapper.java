package bookReviewer.adapter.in.web.rating;

import bookReviewer.adapter.in.web.util.TokenDecoder;
import bookReviewer.business.shared.model.Role;
import bookReviewer.business.useCase.command.deleteRatingUseCase.DeleteRatingCommand;

public class DeleteRatingCommandMapper {
    public static DeleteRatingCommand map(Long ratingId, String token){
        DeleteRatingCommand deleteRatingCommand = new DeleteRatingCommand();
        deleteRatingCommand.setRatingId(ratingId);

        Long userId = TokenDecoder.getUserId(token);
        deleteRatingCommand.setUserId(userId);

        Role role = TokenDecoder.getRole(token);
        deleteRatingCommand.setRole(role);
        return deleteRatingCommand;
    }
}
