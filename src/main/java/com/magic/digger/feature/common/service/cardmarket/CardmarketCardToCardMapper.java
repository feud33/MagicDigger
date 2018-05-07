package com.magic.digger.feature.common.service.cardmarket;

import org.springframework.stereotype.Service;

import com.magic.digger.feature.common.dao.web.cardmarket.CardmarketCard;

@Service
class CardmarketCardToCardMapper {

    public Card create(CardmarketCard cardmarketCard) throws IllegalStateException {
        String cardName = cardmarketCard.getCardName();
        String sellerName = cardmarketCard.getSellerName();
        String comment = cardmarketCard.getComment();
        LanguageEnum language = LanguageEnum.fromString(cardmarketCard.getLanguage());
        int price;
        int quantity;
        try {
            price = Integer.parseInt(cardmarketCard.getPrice().replace(",", ""));
            quantity = Integer.parseInt(cardmarketCard.getQuantity());
        } catch (NumberFormatException e) {
            throw new IllegalStateException(e);
        }
        if( quantity < 0) {
            throw new IllegalStateException("Negative quantity");
        }

        return new Card(sellerName, cardName, language, price, quantity, comment);
    }
}
