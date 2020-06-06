package bookReviewer.application.useCase.command.deleteRatingUseCase;

import bookReviewer.application.boundary.in.useCase.command.DeleteRatingUseCase;
import bookReviewer.application.shared.authorizer.CheckRole;
import bookReviewer.application.shared.mapper.businessToEntity.RoleMapper;
import bookReviewer.application.boundary.out.persistence.DeleteRatingById;
import bookReviewer.entity.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeleteRatingService")
public class DeleteRatingService implements DeleteRatingUseCase {

    @Autowired
    @Qualifier("DeleteRatingByIdService")
    DeleteRatingById deleteRatingById;

    public void deleteRating(DeleteRatingCommand deleteRatingCommand) {
        CheckRole.checkHasMinimumRequiredRole(RoleMapper.map(deleteRatingCommand.getRole()), Role.ADMIN);
        deleteRatingById.deleteRatingById(deleteRatingCommand.getRatingId());
    }
}
