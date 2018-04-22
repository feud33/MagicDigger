package com.magic.digger.feature.bestseller.service.alltrades;

public class AvailableCard {
    private String cardName;
    private int price;

    public AvailableCard(String cardName, int price) {
        this.cardName = cardName;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
