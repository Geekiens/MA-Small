package bookReviewer.adapter.out.emailProvider;

import bookReviewer.application.boundary.out.emailProvider.SendEmailProvider;

public class SendEmailProviderService implements SendEmailProvider {

    EmailAdapter emailAdapter;

    public SendEmailProviderService(EmailAdapter emailAdapter){
        this.emailAdapter = emailAdapter;
    }

    public void sendEmail(String emailOfReceiver, String text, String subject){
        emailAdapter.send(emailOfReceiver, text, subject);
    }

}
