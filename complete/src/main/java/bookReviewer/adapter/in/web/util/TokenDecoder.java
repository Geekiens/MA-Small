package bookReviewer.adapter.in.web.util;

import bookReviewer.business.shared.model.Role;
import bookReviewer.business.util.JwtProvider;
import io.jsonwebtoken.Claims;

import java.util.Date;

public final class TokenDecoder {

    private static void checkIsValidToken(Claims claims){
        if(((long) (int) claims.get("exp")) >= new Date().getTime()){
            throw new InvalidTokenException();
        }
    }

    public static long getUserId(String token) throws InvalidTokenException{
        if (token != null) {
            try {
                Claims claims = JwtProvider.decodeJWT(token);
                checkIsValidToken(claims);
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
            checkIsValidToken(claims);
            return Role.valueOf(claims.get("role").toString().toUpperCase());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new InvalidTokenException();
        }
    }
}
