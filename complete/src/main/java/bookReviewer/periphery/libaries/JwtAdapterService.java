package bookReviewer.periphery.libaries;

import bookReviewer.adapter.in.web.util.token.JwtAdapter;
import bookReviewer.adapter.in.web.util.token.TokenInformation;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.List;


@Service
@Qualifier("JwtAdapterService")
public class JwtAdapterService implements JwtAdapter {
    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    public String createJWT(TokenInformation tokenInformation){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(tokenInformation.getIssuedAt());

        tokenInformation.getCustomFields().forEach((key, value) -> builder.claim(key.toString(), value));

        builder.signWith(signatureAlgorithm, signingKey);
        builder.setExpiration(tokenInformation.getExpirationDate());
        return builder.compact();
    }

    public TokenInformation decodeJWT(String token, List<String> customFields){
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        TokenInformation tokenInformation = new TokenInformation();
        tokenInformation.setExpirationDate(claims.getExpiration());
        tokenInformation.setIssuedAt(claims.getIssuedAt());
        for (String key : customFields){
            tokenInformation.addCustomField(key, claims.get(key));
        }
        return tokenInformation;
    }
}
