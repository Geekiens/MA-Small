package bookReviewer.business.useCase.query.getTokenByLoginUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetTokenByLoginUseCase;
import bookReviewer.business.boundary.out.persistence.FindUserByUsername;
import bookReviewer.business.util.JwtProvider;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.UserRepository;
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

    public String loginUser(String username, String password) throws Exception{
        System.out.println("Username:" + username);
        User user = findUserByUsername.findUserByUsername(username);
        System.out.println(user.getUsername());
        if (user.getPassword().equalsIgnoreCase(get_SHA_1_SecurePassword(password, user.getSalt()))) {
            return getToken(user);
        }
        throw new Exception();
    }

    public String getToken(User user){
        return JwtProvider.createJWT( user);
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
