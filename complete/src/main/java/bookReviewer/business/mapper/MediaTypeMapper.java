package bookReviewer.business.mapper;

import bookReviewer.persistence.model.MediaType;

public class MediaTypeMapper {
    public static MediaType mediaType(bookReviewer.business.model.MediaType mediaType) {
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

    public static bookReviewer.business.model.MediaType mediaTypeBusiness(MediaType mediaType) {
        switch (mediaType) {
            case HARDCOVER:
                return bookReviewer.business.model.MediaType.HARDCOVER;
            case PAPERBACK:
                return bookReviewer.business.model.MediaType.PAPERBACK;
            case EBOOK:
                return bookReviewer.business.model.MediaType.EBOOK;
            case AUDIOBOOK:
                return bookReviewer.business.model.MediaType.AUDIOBOOK;
            default:
                return null;
        }
    }
}
