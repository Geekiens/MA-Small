package bookReviewer.business.mapper.entityToBusiness;

public final class RoleMapper {
    public static bookReviewer.business.shared.Role map(bookReviewer.entity.user.Role role) {
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
