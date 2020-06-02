package bookReviewer.adapter.out.emailProvider;

import bookReviewer.business.boundary.out.emailProvider.SendEmailProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SendEmailProviderService")
public class SendEmailProviderService implements SendEmailProvider {
    @Autowired
    @Qualifier("EmailAdapterService")
    EmailAdapter emailAdapter;

    public void sendEmail(String emailOfReceiver, String text, String subject){
        emailAdapter.send(emailOfReceiver, text, subject);
    }

}
