package bookReviewer.application.useCase.command.registerUserUseCase;

import bookReviewer.application.boundary.in.useCase.command.RegisterUserUseCase;
import bookReviewer.application.shared.mapper.businessToEntity.RoleMapper;
import bookReviewer.application.shared.service.HashingService;
import bookReviewer.application.boundary.out.persistence.SaveUser;
import bookReviewer.entity.user.Credentials;
import bookReviewer.entity.user.User;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RegisterUserService implements RegisterUserUseCase {

    SaveUser saveUser;

    public RegisterUserService(SaveUser saveUser){
        this.saveUser = saveUser;
    }

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
        user.setGender(registerUserCommand.getGender());
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
