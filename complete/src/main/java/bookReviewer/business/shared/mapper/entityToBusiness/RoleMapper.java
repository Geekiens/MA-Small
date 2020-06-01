package bookReviewer.business.shared.mapper.entityToBusiness;

import bookReviewer.business.shared.model.Role;

public final class RoleMapper {
    public static Role map(bookReviewer.entity.user.Role role) {
        switch (role) {
            case ADMIN:
                return Role.ADMIN;
            case MODERATOR:
                return Role.MODERATOR;
            case USER:
                return Role.USER;
            default:
                USER:
                return Role.USER;
        }
    }
}
