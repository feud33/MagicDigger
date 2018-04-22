package com.magic.digger.feature.bestseller.service.web;

public class ForSale {
    private String sellerName;
    private String cardName;
    private LanguageEnum language;
    private int price;
    private int quantity;

    public ForSale(String sellerName, String cardName, LanguageEnum language, int price, int quantity) {
        this.cardName = cardName;
        this.sellerName = sellerName;
        this.language = language;
        this.price = price;
        this.quantity = quantity;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getCardName() {
        return cardName;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
