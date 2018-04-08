package com.magic.digger.dao.web.cardsold;

public class ForSale {
    private String cardName;
    private String sellerName;
    private LanguageEnum language;
    private int price;
    private int quantity;

    public ForSale(String cardName, String sellerName, LanguageEnum language, int price, int quantity) {
        this.cardName = cardName;
        this.sellerName = sellerName;
        this.language = language;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCardName() {
        return cardName;
    }

    public String getSellerName() {
        return sellerName;
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
