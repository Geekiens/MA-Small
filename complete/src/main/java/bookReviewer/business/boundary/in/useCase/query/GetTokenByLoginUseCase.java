package bookReviewer.business.boundary.in.useCase.query;

public interface GetTokenByLoginUseCase {
    String loginUser(String username, String password) throws Exception;
}
