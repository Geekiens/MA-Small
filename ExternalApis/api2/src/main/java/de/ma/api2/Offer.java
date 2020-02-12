package de.ma.api2;

import java.math.BigDecimal;

public class Offer {
    BigDecimal price;
    BigDecimal shippingFee;
    int type;
    boolean available;

    public Offer(BigDecimal price, BigDecimal shippingFee, int type, boolean available) {
        this.price = price;
        this.shippingFee = shippingFee;
        this.type = type;
        this.available = available;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}



