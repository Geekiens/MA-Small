package de.ma.api1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.server.PathParam;
import java.util.ArrayList;

@RestController
public class OfferController {

    @RequestMapping(value = "/offer", method = RequestMethod.GET)
    public ArrayList<Offer> offer(@PathParam(value="isbn") String isbn) {
        ArrayList<Offer> offers;
        switch (isbn) {
            case "000-00-00000-00-1":
                offers = createAllOffers(isbn, 699L);
                offers.remove(3);
                break;
            case "000-00-00000-00-2":
                offers = createAllOffers(isbn, 499L);
                offers.remove(1);
                offers.remove(2);
                break;
            case "000-00-00000-00-3":
                offers = createAllOffers(isbn, 1099L);
                offers.remove(3);
                offers.remove(4);
                break;
            default:
                offers = createAllOffers(isbn, 999L);
                break;
        }
        return offers;
    }


    private ArrayList<Offer> createAllOffers(String isbn, Long price){
        ArrayList<Offer> offers = new ArrayList<>();
        offers.add(new Offer(price + 800,
                generateAffiliateLink(isbn, "hardcover"),
                "hardcover",
                isbn));
        offers.add(new Offer(price + 300,
                generateAffiliateLink(isbn, "paperback"),
                "paperback",
                isbn));
        offers.add(new Offer(price + 100,
                generateAffiliateLink(isbn, "ebook"),
                "ebook",
                isbn));
        offers.add(new Offer(price,
                generateAffiliateLink(isbn, "audiobook"),
                "audiobook",
                isbn));
        return  offers;
    }

    private String generateAffiliateLink (String isbn, String media) {
        return "www.vendor1.de/" + isbn + "?media=" + media + "&affiliate=bookreviewer";
    }
}



