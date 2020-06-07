package bookReviewer.periphery.externalSystems;

import bookReviewer.adapter.out.externalSystems.currencyApi.CurrencyAdapter;
import bookReviewer.adapter.out.externalSystems.currencyApi.CurrencyExchange;
import org.springframework.web.client.RestTemplate;

public class CurrencyAdapterService implements CurrencyAdapter {

    public CurrencyAdapterService(){ }

    public CurrencyExchange getExchangeRateUsdToEuro(){
        RestTemplate restTemplate = new RestTemplate(HttpClientFactory.getClientHttpRequestFactory());
        CurrencyExchange currencyExchange = restTemplate.getForObject(
                "http://localhost:9080/latest?base=USD ",
                CurrencyExchange.class);
        return currencyExchange;
    }

}
