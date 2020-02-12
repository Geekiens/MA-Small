package de.ma.api2;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
public class OfferController {

    @RequestMapping(value = "/offer/{isbn}", method = RequestMethod.GET)
    public ArrayList<Offer> offer(@PathVariable String isbn) {
        ArrayList<Offer> offers;
        switch (isbn) {
            case "000-00-00000-00-1":
                offers = createAllOffers(new BigDecimal("6.99"));
                offers.remove(4);
                break;
            case "000-00-00000-00-2":
                offers = createAllOffers(new BigDecimal("4.99"));
                offers.remove(1);
                break;
            case "000-00-00000-00-3":
                offers = createAllOffers(new BigDecimal("10.99"));
                offers.remove(3);
                offers.remove(4);
                break;
            default:
                offers = createAllOffers(new BigDecimal("9.99"));
                break;
        }
        return offers;
    }

    private ArrayList<Offer> createAllOffers(BigDecimal price){
        ArrayList<Offer> offers = new ArrayList<>();
        offers.add(new Offer(price.add(new BigDecimal("6.00")),
                new BigDecimal("1.99"),
                0,
                true
                ));
        offers.add(new Offer(price.add(new BigDecimal("3.00")),
                new BigDecimal("1.99"),
                1,
                true
                ));
        offers.add(new Offer(price.add(new BigDecimal("1.00")),
                new BigDecimal("0"),
                2,
                true
                ));
        offers.add(new Offer(price,
                new BigDecimal("0"),
                3,
                true
                ));
        return  offers;
    }
}



