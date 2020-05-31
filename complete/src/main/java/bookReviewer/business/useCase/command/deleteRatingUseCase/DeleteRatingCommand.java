package bookReviewer.business.useCase.command.deleteRatingUseCase;

import bookReviewer.business.shared.model.Role;

public class DeleteRatingCommand {
    Long ratingId;
    Role role;

    public DeleteRatingCommand() {
    }

    public DeleteRatingCommand(Long ratingId, Role role) {
        this.ratingId = ratingId;
        this.role = role;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
