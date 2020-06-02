package bookReviewer.presentation.controller.util;

public final class TokenFormatter {
    public static String format(String token){
        String cleanToken = null;
        if (token != null){
            String[] splittedToken = token.split(" ");
            cleanToken = splittedToken[1];

        }
        return cleanToken;
    }
}
