package com.magic.digger.feature.common.service.cardmarket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.magic.digger.feature.common.dao.web.cardmarket.CardmarketCard;
import com.magic.digger.feature.common.dao.web.cardmarket.CardmarketDao;

@Service
public class MagicCardmarketService {
    private final CardmarketDao cardMarketDao;
    private final CardmarketCardToCardMapper cardmarketCardToCardMapper;

    public MagicCardmarketService(CardmarketDao cardMarketDao, CardmarketCardToCardMapper cardmarketCardToCardMapper) {
        this.cardMarketDao = cardMarketDao;
        this.cardmarketCardToCardMapper = cardmarketCardToCardMapper;
    }

    public List<Card> retrieveCardsToSale(WebDriverManager webDriverManager, List<String> cardList) {
        List<Card> cards = new ArrayList<>();

        cardMarketDao.setup(webDriverManager.getDriver());
        cardMarketDao.login(webDriverManager.getDriver());
        for (String card : cardList) {
            cardMarketDao.surfTofindCard(webDriverManager.getDriver(), card);
            cardMarketDao.surfTofilterCard(webDriverManager.getDriver());
            for (CardmarketCard cardmarketCard : cardMarketDao.retrieveCardSolder(webDriverManager.getDriver(), card)) {
                try {
                    cards.add(cardmarketCardToCardMapper.create(cardmarketCard));
                } catch (IllegalStateException e) {
                    // do nothing
                }
            }
        }
        return cards;
    }

    public List<Card> retrievePersonalSales(WebDriverManager webDriverManager, String user) {

        cardMarketDao.setup(webDriverManager.getDriver());
        cardMarketDao.getUserHomePage(webDriverManager.getDriver(), user);
        cardMarketDao.surfToListCards(webDriverManager.getDriver());

        List<CardmarketCard> cardmarketCards = new ArrayList<>();
        cardMarketDao.retrievePersonalCards(webDriverManager.getDriver(), cardmarketCards);
        while(cardMarketDao.hasNextPage(webDriverManager.getDriver())) {
            cardMarketDao.retrievePersonalCards(webDriverManager.getDriver(), cardmarketCards);
        }

        return cardmarketCards.stream()
                .map(cardmarketCardToCardMapper::create)
                .collect(Collectors.toList());
    }
}
