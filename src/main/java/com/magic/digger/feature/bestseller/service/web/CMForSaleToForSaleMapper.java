package com.magic.digger.feature.bestseller.service.web;

import org.springframework.stereotype.Service;

import com.magic.digger.feature.bestseller.dao.web.cardmarket.CMForSale;

@Service
class CMForSaleToForSaleMapper {
    private CMForSaleToForSaleMapper() {

    }

    static ForSale create(String cardName, CMForSale cmForSale) throws IllegalStateException {
        String sellerName = cmForSale.getSellerName();
        LanguageEnum language = LanguageEnum.fromString(cmForSale.getLanguage());
        int price;
        int quantity;
        try {
            price = Integer.parseInt(cmForSale.getPrice().replace(",", ""));
            quantity = Integer.parseInt(cmForSale.getQuantity());
        } catch (NumberFormatException e) {
            throw new IllegalStateException(e);
        }

        return new ForSale(sellerName, cardName, language, price, quantity);
    }
}
