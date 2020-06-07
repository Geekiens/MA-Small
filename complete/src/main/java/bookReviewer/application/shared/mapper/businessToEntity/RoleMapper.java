package bookReviewer.application.shared.mapper.businessToEntity;

import bookReviewer.application.shared.model.Role;

public final class RoleMapper {
    public static bookReviewer.entity.user.Role map(Role role) {
        switch (role) {
            case ADMIN:
                return bookReviewer.entity.user.Role.ADMIN;
            case MODERATOR:
                return bookReviewer.entity.user.Role.MODERATOR;
            case USER:
                return bookReviewer.entity.user.Role.USER;
            default:
                USER:
                return bookReviewer.entity.user.Role.USER;
        }
    }
}
