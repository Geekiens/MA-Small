package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.offerHistory.MediaType;

public final class MediaTypeMapper {
    public static MediaType map(bookReviewer.adapter.out.persistence.model.MediaType mediaType) {
        switch (mediaType) {
            case HARDCOVER:
                return MediaType.HARDCOVER;
            case PAPERBACK:
                return MediaType.PAPERBACK;
            case EBOOK:
                return MediaType.EBOOK;
            case AUDIOBOOK:
                return MediaType.AUDIOBOOK;
            default:
                USER:
                return null;
        }
    }
}
