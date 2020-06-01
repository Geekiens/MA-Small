package bookReviewer.adapter.in.web.user;

import bookReviewer.adapter.in.web.util.RegisterUserException;
import bookReviewer.business.boundary.in.useCase.command.RegisterUserUseCase;
import bookReviewer.business.boundary.in.useCase.query.GetTokenByLoginUseCase;
import bookReviewer.business.shared.model.Role;
import bookReviewer.business.useCase.command.registerUserUseCase.RegisterUserCommand;
import bookReviewer.business.useCase.query.getTokenByLoginUseCase.LoginInput;
import bookReviewer.business.useCase.query.getTokenByLoginUseCase.LoginOutput;
import bookReviewer.business.util.JwtProvider;
import bookReviewer.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("UserAdapterService")
public class UserAdapterService implements UserAdapter {

    @Autowired
    @Qualifier("RegisterUserService")
    RegisterUserUseCase registerUserUseCase;

    @Autowired
    @Qualifier("GetTokenByLoginService")
    GetTokenByLoginUseCase getTokenByLoginUseCase;

    public void registerUser(String username, String password, String email){
        RegisterUserCommand registerUserCommand = new RegisterUserCommand();
        registerUserCommand.setUsername(username);
        registerUserCommand.setPassword(password);
        registerUserCommand.setEmail(email);
        registerUserCommand.setRole(Role.USER);
        try {
            registerUserUseCase.registerUser(registerUserCommand);
        } catch (Exception e){
            e.printStackTrace();
            throw new RegisterUserException();

        }
    }

    public String loginUser(String username, String password){
        LoginInput loginInput = new LoginInput(username, password);
        try {
            LoginOutput loginOutput = getTokenByLoginUseCase.loginUser(loginInput);
            String token = JwtProvider.createJWT(loginOutput);
            return token;

        }catch (Exception e){
           e.printStackTrace();
            throw new RegisterUserException();

        }

    }
}
