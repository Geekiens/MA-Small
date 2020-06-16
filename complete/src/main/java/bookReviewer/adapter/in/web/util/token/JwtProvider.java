package bookReviewer.adapter.in.web.util.token;

import bookReviewer.application.useCase.query.getTokenByLoginUseCase.LoginOutput;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class JwtProvider {

    JwtAdapter jwtAdapter;

    public JwtProvider(JwtAdapter jwtAdapter){
        this.jwtAdapter = jwtAdapter;
    }

    public String createJWT(LoginOutput loginOutput) {
        TokenInformation tokenInformation = new TokenInformation();

        long nowMillis = System.currentTimeMillis();
        tokenInformation.setIssuedAt(new Date(nowMillis));

        tokenInformation.addCustomField("username", loginOutput.getUsername());
        tokenInformation.addCustomField("role", loginOutput.getRole().getRolename());
        tokenInformation.addCustomField("userId", loginOutput.getUserId());

        Date exp = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(exp);
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        tokenInformation.setExpirationDate(calendar.getTime());

        return jwtAdapter.createJWT(tokenInformation);
    }

    public TokenInformation decodeJWT(String jwt) {
        ArrayList<String> customFields = new ArrayList<>();
        customFields.add("username");
        customFields.add("role");
        customFields.add("userId");
        return jwtAdapter.decodeJWT(jwt, customFields);
    }
}
