package bookReviewer.application.useCase.query.getTokenByLoginUseCase;

import bookReviewer.application.boundary.in.useCase.query.GetTokenByLoginUseCase;
import bookReviewer.application.shared.mapper.entityToBusiness.RoleMapper;
import bookReviewer.application.shared.service.HashingService;
import bookReviewer.application.boundary.out.persistence.FindUserByUsername;
import bookReviewer.entity.user.User;

public class GetTokenByLoginService implements GetTokenByLoginUseCase {

    FindUserByUsername findUserByUsername;

    public GetTokenByLoginService(FindUserByUsername findUserByUsername){
        this.findUserByUsername = findUserByUsername;
    }

    public LoginOutput loginUser(LoginInput loginInput) throws Exception{
        System.out.println("Username:" + loginInput.getUsername());
        User user = findUserByUsername.findUserByUsername(loginInput.getUsername());
        System.out.println(user.getCredentials().getUsername());
        if (user.getCredentials().getPassword().equalsIgnoreCase(HashingService.get_SHA_1_SecurePassword(loginInput.getPassword(), user.getCredentials().getSalt()))) {
            LoginOutput loginOutput = new LoginOutput();
            loginOutput.setRole(RoleMapper.map(user.getRole()));
            loginOutput.setUserId(user.getId());
            loginOutput.setUsername(user.getCredentials().getUsername());
            return loginOutput;
        }
        throw new Exception();
    }
}
