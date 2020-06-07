package bookReviewer.application.boundary.in.useCase.query;

import bookReviewer.application.useCase.query.getTokenByLoginUseCase.LoginInput;
import bookReviewer.application.useCase.query.getTokenByLoginUseCase.LoginOutput;

public interface GetTokenByLoginUseCase {
    LoginOutput loginUser(LoginInput loginInput) throws Exception;
}
