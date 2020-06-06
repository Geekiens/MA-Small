package bookReviewer.application.boundary.out.emailProvider;

public interface SendEmailProvider {
    void sendEmail(String emailOfReceiver, String text, String subject);
}
