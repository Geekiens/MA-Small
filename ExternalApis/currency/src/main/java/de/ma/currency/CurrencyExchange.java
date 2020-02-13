package de.ma.currency;

public class CurrencyExchange {
    Rates rates;
    String base;

    public CurrencyExchange() {
    }

    public CurrencyExchange(Rates rates, String base) {
        this.rates = rates;
        this.base = base;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }


}

