package bookReviewer.business;

import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.persistence.model.Role;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void registerUser(String username, String password, String email, Role role) throws Exception {
        byte[] salt = getSalt();
        String hashedPassword = get_SHA_1_SecurePassword(password, salt);
        User user = new User(username, hashedPassword, email, salt, role);
        userRepository.save(user);
    }

    public String loginUser(String username, String password) throws Exception{
        System.out.println("Username:" + username);
        User user = userRepository.findByUsername(username);
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

    public User getUser (long userId) {
        User user = userRepository.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User"));
        return  user;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

}
