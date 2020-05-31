package bookReviewer.business.useCase.command.registerUserUseCase;

import bookReviewer.business.boundary.in.useCase.command.RegisterUserUseCase;
import bookReviewer.business.boundary.out.persistence.SaveUser;
import bookReviewer.business.mapper.businessToEntity.UserMapper;
import bookReviewer.business.shared.model.Role;
import bookReviewer.business.model.UserBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@Qualifier("RegisterUserService")
public class RegisterUserService implements RegisterUserUseCase {

    @Autowired
    @Qualifier("SaveUserService")
    SaveUser saveUser;

    public void registerUser(String username, String password, String email, Role role) throws Exception {
        byte[] salt = getSalt();
        String hashedPassword = get_SHA_1_SecurePassword(password, salt);
        UserBusiness user = new UserBusiness(username, hashedPassword, email, salt, role);
        saveUser.saveUser(UserMapper.map(user, null));
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

    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
