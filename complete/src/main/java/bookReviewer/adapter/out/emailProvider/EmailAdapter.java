package bookReviewer.adapter.out.emailProvider;

public interface EmailAdapter {
    void send(String emailOfReceiver, String text, String subject);
}
