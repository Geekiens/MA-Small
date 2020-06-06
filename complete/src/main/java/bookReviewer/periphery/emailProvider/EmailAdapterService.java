package bookReviewer.periphery.emailProvider;

import bookReviewer.adapter.out.emailProvider.EmailAdapter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class EmailAdapterService implements EmailAdapter {

    public EmailAdapterService(){}

    public void send(String emailOfReceiver, String text, String subject) {
        final String emailSender = "max.master.thesis2@gmail.com";
        final String password = "supersafepassword";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailSender, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailSender));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailOfReceiver)
            );
            message.setSubject(subject);
            message.setText(text);

            System.out.println("Sending Email to: " + emailOfReceiver);
            Transport.send(message);
            System.out.println("Email sent to: " + emailOfReceiver);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
