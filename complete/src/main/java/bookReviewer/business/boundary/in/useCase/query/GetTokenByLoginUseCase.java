package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.useCase.query.getTokenByLoginUseCase.LoginInput;
import bookReviewer.business.useCase.query.getTokenByLoginUseCase.LoginOutput;

public interface GetTokenByLoginUseCase {
    LoginOutput loginUser(LoginInput loginInput) throws Exception;
}
