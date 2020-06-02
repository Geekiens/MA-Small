package bookReviewer.periphery.externalSystems;

import bookReviewer.adapter.out.externalSystems.buchLaden123.Buchladen123Adapter;
import bookReviewer.adapter.out.externalSystems.buchVerkauf24.BuchVerkauf24Adapter;
import bookReviewer.adapter.out.externalSystems.buchVerkauf24.OfferApi2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Qualifier("Buchverkauf24AdapterService")
public class Buchverkauf24AdapterService implements BuchVerkauf24Adapter {
    public OfferApi2[] queryOffers(String isbn) throws Exception{

        RestTemplate restTemplate = new RestTemplate(HttpClientFactory.getClientHttpRequestFactory());
        ResponseEntity<OfferApi2[]> response = restTemplate.getForEntity(
                "http://localhost:9091/offer/" + isbn,
                OfferApi2[].class);
        OfferApi2[] offerApi1s = response.getBody();
        if (response.getStatusCode().isError()) throw new Exception();
        return offerApi1s;
    }
}
