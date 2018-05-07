package com.magic.digger.feature.common.dao.web.cardmarket;

public class CardmarketCard {
    private String cardName;
    private String sellerName;
    private String language;
    private String price;
    private String quantity;
    private String comment;

    //<WBNRV><CAERV><mana>-<cardid>

    public CardmarketCard(String card, String sellerName, String language, String price, String quantity) {
        this.cardName = card;
        this.sellerName = sellerName;
        this.language = language;
        this.price = price;
        this.quantity = quantity;
    }

    public CardmarketCard(String card, String language, String price, String quantity) {
        this.cardName = card;
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

    public String getLanguage() {
        return language;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override public String toString() {
        return "CardmarketCard{" +
                "cardName='" + cardName + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", language='" + language + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
