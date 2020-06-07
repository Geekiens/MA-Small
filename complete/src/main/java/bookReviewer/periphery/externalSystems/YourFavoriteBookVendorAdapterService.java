package bookReviewer.periphery.externalSystems;

import bookReviewer.adapter.out.externalSystems.yourFavoriteBookVendor.OfferApi3;
import bookReviewer.adapter.out.externalSystems.yourFavoriteBookVendor.YourFavoriteBookVendorAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class YourFavoriteBookVendorAdapterService implements YourFavoriteBookVendorAdapter {

    public YourFavoriteBookVendorAdapterService(){}

    public OfferApi3[] queryOffers(String isbn) throws Exception{
        RestTemplate restTemplate = new RestTemplate(HttpClientFactory.getClientHttpRequestFactory());

        ResponseEntity<OfferApi3[]> response = restTemplate.getForEntity(
                "http://localhost:9092/offer/" + isbn,
                OfferApi3[].class);
        if (response.getStatusCode().isError()) throw new Exception();

        OfferApi3[] offerApi3s = response.getBody();
        return offerApi3s;
    }
}
