package bookReviewer.business.shared.authorizer;

import bookReviewer.business.exception.ForbiddenInteractionException;
import bookReviewer.entity.user.Role;

public final class CheckRole {
    public static void checkHasMinimumRequiredRole(Role userRole, Role minimumRole) {
        if (minimumRole == Role.USER) {
            if(userRole == Role.USER || userRole == Role.MODERATOR || userRole == Role.ADMIN){
                return;
            }
            throw new ForbiddenInteractionException();
        }
        if (minimumRole == Role.MODERATOR) {
            if(userRole == Role.ADMIN || userRole == Role.MODERATOR){
                return;
            }
            throw new ForbiddenInteractionException();
        }
        if (userRole == Role.ADMIN){
            return;
        }
        throw new ForbiddenInteractionException();
    }
}
