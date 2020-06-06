package bookReviewer.adapter.in.web.user;

import bookReviewer.adapter.in.web.util.RegisterUserException;
import bookReviewer.application.boundary.in.useCase.command.RegisterUserUseCase;
import bookReviewer.application.boundary.in.useCase.query.GetTokenByLoginUseCase;
import bookReviewer.application.shared.model.Role;
import bookReviewer.application.useCase.command.registerUserUseCase.RegisterUserCommand;
import bookReviewer.application.useCase.query.getTokenByLoginUseCase.LoginInput;
import bookReviewer.application.useCase.query.getTokenByLoginUseCase.LoginOutput;
import bookReviewer.adapter.in.web.util.token.JwtProvider;
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

    @Autowired
    JwtProvider jwtProvider;

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
            System.out.println(loginOutput.getUsername());
            String token = jwtProvider.createJWT(loginOutput);
            return token;

        }catch (Exception e){
           e.printStackTrace();
            throw new RegisterUserException();

        }

    }
}
