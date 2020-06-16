package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.adapter.out.persistence.model.ActivityType;

public final class ActivityTypeMapper {
    public static bookReviewer.entity.user.ActivityType map(ActivityType activityType){
        switch (activityType) {
            case BOOK_CREATED:
                return bookReviewer.entity.user.ActivityType.BOOK_CREATED;
            case RATING_CREATED_WITH_COMMENT:
                return bookReviewer.entity.user.ActivityType.RATING_CREATED_WITH_COMMENT;
            case RATING_CREATED:
                return bookReviewer.entity.user.ActivityType.RATING_CREATED;
            case BOOK_DELETED_BY_ADMIN:
                return bookReviewer.entity.user.ActivityType.BOOK_DELETED_BY_ADMIN;
            case RATING_DELETED_BY_ADMIN:
                return bookReviewer.entity.user.ActivityType.RATING_DELETED_BY_ADMIN;
            case RATING_DELETED_BY_MODERATOR:
                return bookReviewer.entity.user.ActivityType.RATING_DELETED_BY_MODERATOR;
            default: return null;
        }
    }
}
