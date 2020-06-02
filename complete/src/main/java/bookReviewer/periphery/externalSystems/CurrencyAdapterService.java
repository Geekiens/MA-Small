package bookReviewer.periphery.externalSystems;

import bookReviewer.adapter.out.externalSystems.currencyApi.CurrencyAdapter;
import bookReviewer.adapter.out.externalSystems.currencyApi.CurrencyExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Qualifier("CurrencyAdapterService")
public class CurrencyAdapterService implements CurrencyAdapter {
    public CurrencyExchange getExchangeRateUsdToEuro(){
        RestTemplate restTemplate = new RestTemplate(HttpClientFactory.getClientHttpRequestFactory());
        CurrencyExchange currencyExchange = restTemplate.getForObject(
                "http://localhost:9080/latest?base=USD ",
                CurrencyExchange.class);
        return currencyExchange;
    }

}
