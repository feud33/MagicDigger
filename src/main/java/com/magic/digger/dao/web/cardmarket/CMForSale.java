package com.magic.digger.dao.web.cardmarket;

public class CMForSale {
    private String sellerName;
    private String language;
    private String price;
    private String quantity;

    public CMForSale(String sellerName, String language, String price, String quantity) {
        this.sellerName = sellerName;
        this.language = language;
        this.price = price;
        this.quantity = quantity;
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
}
