package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.entity.offerHistory.MediaType;

public class MediaTypeMapper {
    public static bookReviewer.persistence.model.MediaType map(MediaType mediaType) {
        switch (mediaType) {
            case HARDCOVER:
                return bookReviewer.persistence.model.MediaType.HARDCOVER;
            case PAPERBACK:
                return bookReviewer.persistence.model.MediaType.PAPERBACK;
            case EBOOK:
                return bookReviewer.persistence.model.MediaType.EBOOK;
            case AUDIOBOOK:
                return bookReviewer.persistence.model.MediaType.AUDIOBOOK;
            default:
                return null;
        }
    }
}
