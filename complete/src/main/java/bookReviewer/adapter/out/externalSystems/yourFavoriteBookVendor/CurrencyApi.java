package bookReviewer.adapter.out.externalSystems.yourFavoriteBookVendor;

import bookReviewer.adapter.out.externalSystems.HttpClientFactory;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public final class CurrencyApi {
    public static BigDecimal getCurrencyExchange() {
        BigDecimal exchangeRate = new BigDecimal(1);
        try {
            RestTemplate restTemplate = new RestTemplate(HttpClientFactory.getClientHttpRequestFactory());

            CurrencyExchange currencyExchange = restTemplate.getForObject(
                    "http://localhost:9080/latest?base=USD ",
                    CurrencyExchange.class);
            return currencyExchange.getRates().getEUR();
        } catch (Exception e) {
            System.out.println("Failed to query exchange rate");
            e.printStackTrace();
            return exchangeRate;
        }
    }

}
