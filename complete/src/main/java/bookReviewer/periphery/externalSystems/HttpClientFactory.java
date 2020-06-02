package bookReviewer.adapter.out.externalSystems;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class HttpClientFactory {
    public static SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(8000);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(8000);
        return clientHttpRequestFactory;
    }
}
