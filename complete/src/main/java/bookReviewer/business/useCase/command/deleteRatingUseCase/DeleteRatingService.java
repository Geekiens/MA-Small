package bookReviewer.business.useCase.command.deleteRatingUseCase;

import bookReviewer.business.boundary.in.useCase.command.DeleteRatingUseCase;
import bookReviewer.business.boundary.out.persistence.DeleteRatingById;
import bookReviewer.business.shared.authorizer.CheckRole;
import bookReviewer.business.shared.mapper.businessToEntity.RoleMapper;
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
