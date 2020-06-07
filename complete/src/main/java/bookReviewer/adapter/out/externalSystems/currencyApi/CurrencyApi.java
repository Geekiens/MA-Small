package bookReviewer.adapter.out.externalSystems.currencyApi;

import java.math.BigDecimal;


public final class CurrencyApi {

    CurrencyAdapter currencyAdapter;

    public CurrencyApi(CurrencyAdapter currencyAdapter){
        this.currencyAdapter = currencyAdapter;
    }

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
