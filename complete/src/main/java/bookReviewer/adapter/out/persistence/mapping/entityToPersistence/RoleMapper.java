package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.entity.user.Role;

public final class RoleMapper {
    public static bookReviewer.adapter.out.persistence.model.Role map(Role role) {
        switch (role) {
            case ADMIN:
                return bookReviewer.adapter.out.persistence.model.Role.ADMIN;
            case MODERATOR:
                return bookReviewer.adapter.out.persistence.model.Role.MODERATOR;
            case USER:
                return bookReviewer.adapter.out.persistence.model.Role.USER;
            default:
                USER:
                return bookReviewer.adapter.out.persistence.model.Role.USER;
        }
    }
}
