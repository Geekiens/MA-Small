package de.ma.api3;

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
                offers = createAllOffers(new BigDecimal("6.99"), isbn);
                offers.remove(4);
                break;
            case "000-00-00000-00-2":
                offers = createAllOffers(new BigDecimal("3.99"), isbn);
                offers.remove(1);
                break;
            case "000-00-00000-00-3":
                offers = createAllOffers(new BigDecimal("12.99"), isbn);
                offers.remove(3);
                offers.remove(4);
                break;
            default:
                offers = createAllOffers(new BigDecimal("10.99"), isbn);
                break;
        }
        return offers;
    }

    private ArrayList<Offer> createAllOffers(BigDecimal price, String isbn){
        ArrayList<Offer> offers = new ArrayList<>();
        offers.add(new Offer(price.add(new BigDecimal("6.00")),
                "EUR",
                0,
                isbn
                ));
        offers.add(new Offer(price.add(new BigDecimal("3.00")),
                "EUR",
                1,
                isbn
                ));
        offers.add(new Offer(price,
                "USD",
                2,
                isbn
                ));
        offers.add(new Offer(price,
                "USD",
                3,
                isbn
                ));
        return  offers;
    }

    private String generateAffiliateLink (String isbn, String media) {
        return "www.vendor3.de/" + isbn + "?media=" + media + "&affiliate=bookreviewer";
    }
}



