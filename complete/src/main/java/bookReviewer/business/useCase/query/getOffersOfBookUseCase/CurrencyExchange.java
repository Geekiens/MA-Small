package bookReviewer.business.useCase.query.getOffersOfBookUseCase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class CurrencyExchange {
    Rates rates;
    String base;

    public CurrencyExchange() {
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

