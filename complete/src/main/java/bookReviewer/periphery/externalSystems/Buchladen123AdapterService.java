package bookReviewer.periphery.externalSystems;

import bookReviewer.adapter.out.externalSystems.buchLaden123.Buchladen123Adapter;
import bookReviewer.adapter.out.externalSystems.buchLaden123.OfferApi1;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Qualifier("Buchladen123AdapterService")
public class Buchladen123AdapterService implements Buchladen123Adapter {
    public OfferApi1[] queryOffers(String isbn) throws Exception{

        RestTemplate restTemplate = new RestTemplate(HttpClientFactory.getClientHttpRequestFactory());
        ResponseEntity<OfferApi1[]> response = restTemplate.getForEntity(
                "http://localhost:9090/offer?isbn=" + isbn,
                OfferApi1[].class);
        OfferApi1[] offerApi1s = response.getBody();
        if (response.getStatusCode().isError()) throw new Exception();
        return offerApi1s;
    }
}
