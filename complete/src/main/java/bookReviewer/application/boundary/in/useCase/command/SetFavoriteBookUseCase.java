package bookReviewer.application.boundary.in.useCase.command;

public interface SetFavoriteBookUseCase {
    void addFavorite(Long userId, Long bookId);
}
