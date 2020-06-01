package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.adapter.out.persistence.model.ActivityType;

public final class ActivityTypeMapper {
    public static ActivityType map(bookReviewer.entity.user.ActivityType activityType){
        switch (activityType) {
            case BOOK_CREATED:
                return ActivityType.BOOK_CREATED;
            case RATING_CREATED_WITH_COMMENT:
                return ActivityType.RATING_CREATED_WITH_COMMENT;
            case RATING_CREATED:
                return ActivityType.RATING_CREATED;
            case BOOK_DELETED_BY_ADMIN:
                return ActivityType.BOOK_DELETED_BY_ADMIN;
            case RATING_DELETED_BY_ADMIN:
                return ActivityType.RATING_DELETED_BY_ADMIN;
            case RATING_DELETED_BY_MODERATOR:
                return ActivityType.RATING_DELETED_BY_MODERATOR;
            default: return null;
        }
    }
}
