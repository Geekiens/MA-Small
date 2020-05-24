package bookReviewer.business.boundary.in.useCase.query;

public interface GetTokenByLoginQuery {
    String loginUser(String username, String password) throws Exception;
}
