package bookReviewer.business.mapper;

import bookReviewer.persistence.model.Role;

public class RoleMapper {
    public static bookReviewer.persistence.model.Role role(bookReviewer.business.shared.Role role) {
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

    public static bookReviewer.business.shared.Role roleBusiness(bookReviewer.persistence.model.Role role) {
        switch (role) {
            case ADMIN:
                return bookReviewer.business.shared.Role.ADMIN;
            case MODERATOR:
                return bookReviewer.business.shared.Role.MODERATOR;
            case USER:
                return bookReviewer.business.shared.Role.USER;
            default:
                USER:
                return bookReviewer.business.shared.Role.USER;
        }
    }
}
