package bookReviewer.business.mapper;

import bookReviewer.persistence.model.Role;

public class RoleMapper {
    public static bookReviewer.persistence.model.Role role(bookReviewer.business.model.Role role) {
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

    public static bookReviewer.business.model.Role roleBusiness(bookReviewer.persistence.model.Role role) {
        switch (role) {
            case ADMIN:
                return bookReviewer.business.model.Role.ADMIN;
            case MODERATOR:
                return bookReviewer.business.model.Role.MODERATOR;
            case USER:
                return bookReviewer.business.model.Role.USER;
            default:
                USER:
                return bookReviewer.business.model.Role.USER;
        }
    }
}
