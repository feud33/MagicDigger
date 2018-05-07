package com.magic.digger.feature.common.service.cardmarket;

public class Card implements Comparable {
    private String sellerName;
    private String cardName;
    private LanguageEnum language;
    private int price;
    private int quantity;
    private String comment;
    private String toCompare;

    public Card(String sellerName, String cardName, LanguageEnum language, int price, int quantity) {
        this.cardName = cardName;
        this.sellerName = sellerName;
        this.language = language;
        this.price = price;
        this.quantity = quantity;
        this.toCompare = cardName;
    }

    public Card(String sellerName, String cardName, LanguageEnum language, int price, int quantity, String comment) {
        this.cardName = cardName;
        this.sellerName = sellerName;
        this.language = language;
        this.price = price;
        this.quantity = quantity;
        this.comment = comment;
        this.toCompare = comment;
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

    public String getComment() {
        return comment;
    }

    public String getToCompare() {
        return toCompare;
    }

    @Override public int compareTo(Object card) {
        return this.comment.compareTo(((Card) card).getToCompare());
    }
}
