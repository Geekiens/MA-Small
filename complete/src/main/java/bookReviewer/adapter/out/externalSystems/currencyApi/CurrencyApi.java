package bookReviewer.adapter.out.externalSystems.currencyApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public final class CurrencyApi {

    @Autowired
    @Qualifier("CurrencyAdapterService")
    CurrencyAdapter currencyAdapter;

    public BigDecimal getCurrencyExchange() {
        BigDecimal exchangeRate = new BigDecimal(1); // fallback
        try {
            CurrencyExchange currencyExchange = currencyAdapter.getExchangeRateUsdToEuro();
            return currencyExchange.getRates().getEUR();
        } catch (Exception e) {
            System.out.println("Failed to query exchange rate");
            e.printStackTrace();
            return exchangeRate;
        }
    }

}
