package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.user.Role;

public final class RoleMapper {
    public static Role map(bookReviewer.persistence.model.Role rolePersistence) {
        switch (rolePersistence) {
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
