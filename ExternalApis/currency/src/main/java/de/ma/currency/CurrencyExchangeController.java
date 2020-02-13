package de.ma.currency;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
public class CurrencyExchangeController {

    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public CurrencyExchange getCurrencyExchange(@PathParam("base") String base) {
       Rates rates = new Rates(new BigDecimal(0.92), new BigDecimal(1.48));
       CurrencyExchange currencyExchange = new CurrencyExchange(rates, "USD");
       return currencyExchange;
    }

}



