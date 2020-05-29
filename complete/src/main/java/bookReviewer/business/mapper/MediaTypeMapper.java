package bookReviewer.business.mapper;

import bookReviewer.business.shared.MediaType;

public class MediaTypeMapper {
    public static MediaType mediaTypeBusiness(bookReviewer.persistence.model.MediaType mediaType) {
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

    public static bookReviewer.persistence.model.MediaType mediaType(MediaType mediaType) {
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
