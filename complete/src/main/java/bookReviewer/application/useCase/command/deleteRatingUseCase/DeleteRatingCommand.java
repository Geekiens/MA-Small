package bookReviewer.application.useCase.command.deleteRatingUseCase;

import bookReviewer.application.shared.model.Role;

public class DeleteRatingCommand {
    Long ratingId;
    Role role;
    Long userId;

    public DeleteRatingCommand() {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
