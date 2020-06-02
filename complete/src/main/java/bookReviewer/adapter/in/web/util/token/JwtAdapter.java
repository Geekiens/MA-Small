package bookReviewer.adapter.in.web.util.token;

import java.util.List;

public interface JwtAdapter {
    String createJWT(TokenInformation tokenInformation);
    TokenInformation decodeJWT(String token, List<String> customFields);
}
