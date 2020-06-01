package bookReviewer.business.useCase.query.getTokenByLoginUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetTokenByLoginUseCase;
import bookReviewer.business.boundary.out.persistence.FindUserByUsername;
import bookReviewer.business.shared.mapper.entityToBusiness.RoleMapper;
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
        if (user.getCredentials().getPassword().equalsIgnoreCase(get_SHA_1_SecurePassword(loginInput.getPassword(), user.getCredentials().getSalt()))) {
            LoginOutput loginOutput = new LoginOutput();
            loginOutput.setRole(RoleMapper.map(user.getRole()));
            loginOutput.setUserId(user.getId());
            loginOutput.setUsername(user.getCredentials().getUsername());
            return loginOutput;
        }
        throw new Exception();
    }

    private static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
