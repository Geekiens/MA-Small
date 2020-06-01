package bookReviewer.business.shared.mapper.businessToEntity;

import bookReviewer.entity.offerHistory.MediaType;

public class MediaTypeMapper {
    public static MediaType map(bookReviewer.business.shared.model.MediaType mediaType) {
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
                return null;
        }
    }
}
