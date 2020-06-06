package bookReviewer.periphery.externalSystems;

import bookReviewer.adapter.out.externalSystems.buchVerkauf24.BuchVerkauf24Adapter;
import bookReviewer.adapter.out.externalSystems.buchVerkauf24.OfferApi2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class Buchverkauf24AdapterService implements BuchVerkauf24Adapter {

    public Buchverkauf24AdapterService(){}

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
