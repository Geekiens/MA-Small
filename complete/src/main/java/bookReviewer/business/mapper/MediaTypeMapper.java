package bookReviewer.business.mapper;

import bookReviewer.business.shared.model.MediaType;

public class MediaTypeMapper {
    public static MediaType mediaTypeBusiness(bookReviewer.entity.offerHistory.MediaType mediaType) {
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
