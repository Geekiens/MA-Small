package bookReviewer.adapter.in.web.util.token;

import bookReviewer.adapter.in.web.util.InvalidTokenException;
import bookReviewer.application.shared.model.Role;

import java.util.Date;

public final class TokenDecoder {

    JwtProvider jwtProvider;

    public TokenDecoder(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }

    private static void checkIsValidToken(TokenInformation tokenInformation){
        if(((long) (int) tokenInformation.getExpirationDate().getTime()) >= new Date().getTime()){
            throw new InvalidTokenException();
        }
    }

    public long getUserId(String token) throws InvalidTokenException{
        if (token != null) {
            try {
                TokenInformation tokenInformation = jwtProvider.decodeJWT(token);
                checkIsValidToken(tokenInformation);
                return Long.parseLong(tokenInformation.getCustomFieldValue("userId").toString());
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new InvalidTokenException();
            }
        }
        throw new InvalidTokenException();
    }

    public Role getRole(String token) throws  InvalidTokenException{
        try {
            TokenInformation tokenInformation = jwtProvider.decodeJWT(token);
            checkIsValidToken(tokenInformation);
            return Role.valueOf(tokenInformation.getCustomFieldValue("role").toString().toUpperCase());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new InvalidTokenException();
        }
    }
}
