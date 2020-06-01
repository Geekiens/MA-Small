package bookReviewer.business.useCase.command.registerUserUseCase;

import bookReviewer.business.boundary.in.useCase.command.RegisterUserUseCase;
import bookReviewer.business.boundary.out.persistence.SaveUser;
import bookReviewer.business.shared.mapper.businessToEntity.RoleMapper;
import bookReviewer.business.shared.service.HashingService;
import bookReviewer.entity.user.Credentials;
import bookReviewer.entity.user.User;
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

    public void registerUser(RegisterUserCommand registerUserCommand) throws Exception {
        byte[] salt = getSalt();
        String hashedPassword = HashingService.get_SHA_1_SecurePassword(registerUserCommand.getPassword(), salt);
        Credentials credentials = new Credentials();
        credentials.setUsername(registerUserCommand.getUsername());
        credentials.setPassword(hashedPassword);
        credentials.setSalt(salt);
        User user = new User();
        user.setCredentials(credentials);
        user.setEmail(registerUserCommand.getEmail());
        user.setRole(RoleMapper.map(registerUserCommand.getRole()));
        saveUser.saveUser(user);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
