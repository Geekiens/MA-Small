package bookReviewer.business.useCase.query.getTokenByLoginUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetTokenByLoginUseCase;
import bookReviewer.business.boundary.out.persistence.FindUserByUsername;
import bookReviewer.business.shared.mapper.entityToBusiness.RoleMapper;
import bookReviewer.business.shared.service.HashingService;
import bookReviewer.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Qualifier("GetTokenByLoginService")
public class GetTokenByLoginService implements GetTokenByLoginUseCase {

    @Autowired
    @Qualifier("FindUserByUsernameService")
    FindUserByUsername findUserByUsername;

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
