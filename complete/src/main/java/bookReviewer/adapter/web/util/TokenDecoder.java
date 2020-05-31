package bookReviewer.adapter.web.util;

import bookReviewer.business.shared.model.Role;
import bookReviewer.business.util.JwtProvider;
import io.jsonwebtoken.Claims;

import java.util.Optional;

public final class TokenDecoder {
    public static Optional<Long> getUserIdIfPresent(String token) throws InvalidTokenException{
        if (token != null) {
            try {
                Claims claims = JwtProvider.decodeJWT(token);
                long userId = (long) (int) claims.get("userId");
                return Optional.of(userId);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new InvalidTokenException();
            }
        }
        throw new InvalidTokenException();
    }

    public static long getUserId(String token) throws InvalidTokenException{
        if (token != null) {
            try {
                Claims claims = JwtProvider.decodeJWT(token);
                return ((long) (int) claims.get("userId"));
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new InvalidTokenException();
            }
        }
        throw new InvalidTokenException();
    }

    public static Role getRole(String token) throws  InvalidTokenException{
        try {
            Claims claims = JwtProvider.decodeJWT(token);
            return Role.valueOf(claims.get("role").toString().toUpperCase());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new InvalidTokenException();
        }
    }
}
