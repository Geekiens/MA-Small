package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.entity.offerHistory.MediaType;

public class MediaTypeMapper {
    public static bookReviewer.adapter.out.persistence.model.MediaType map(MediaType mediaType) {
        switch (mediaType) {
            case HARDCOVER:
                return bookReviewer.adapter.out.persistence.model.MediaType.HARDCOVER;
            case PAPERBACK:
                return bookReviewer.adapter.out.persistence.model.MediaType.PAPERBACK;
            case EBOOK:
                return bookReviewer.adapter.out.persistence.model.MediaType.EBOOK;
            case AUDIOBOOK:
                return bookReviewer.adapter.out.persistence.model.MediaType.AUDIOBOOK;
            default:
                return null;
        }
    }
}
