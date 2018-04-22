package com.magic.digger.feature.bestseller.service.alltrades;

public class DealDetailElement {
    private String cardName;
    private int price;

    public DealDetailElement(String carName, int price) {
        this.cardName = carName;
        this.price = price;
    }

    public String getCardName() {
        return cardName;
    }

    public int getPrice() {
        return price;
    }
}
