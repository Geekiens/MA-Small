package hello.business;

import hello.business.model.Offer;

import java.util.ArrayList;

public class OfferService {

    public ArrayList<Offer> getOffers() {
        ArrayList<Offer> allOffers = new ArrayList<Offer>();
        allOffers.addAll(getThaliaOffers());
        allOffers.addAll(getHugendubelOffers());
        allOffers.addAll(getAmazonOffers());
        return allOffers;
    }

    public ArrayList<Offer> getThaliaOffers() {
        // TODO: Request to Mock API, Timeout and Fallback to Cached data
        return new ArrayList<Offer>();
    }

    public ArrayList<Offer> getHugendubelOffers() {
        return new ArrayList<Offer>();
    }

    public ArrayList<Offer> getAmazonOffers() {
        return new ArrayList<Offer>();
    }
}
