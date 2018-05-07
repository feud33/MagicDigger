package com.magic.digger.feature.bestseller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.magic.digger.feature.bestseller.service.alltrades.AllTradesService;
import com.magic.digger.feature.bestseller.service.alltrades.SellerCardsDetail;
import com.magic.digger.feature.bestseller.service.besttrades.BestTradeService;
import com.magic.digger.feature.bestseller.service.besttrades.Deal;
import com.magic.digger.feature.common.service.cardmarket.Card;
import com.magic.digger.feature.common.service.cardmarket.MagicCardmarketService;
import com.magic.digger.feature.common.service.cardmarket.WebDriverManager;

@Controller public class BestSellersController {
    private MagicCardmarketService magicCardmarketService;
    private AllTradesService allTradesService;
    private BestTradeService bestTradesService;

    @Autowired
    public BestSellersController(MagicCardmarketService magicCardmarketService, AllTradesService allTradesService, BestTradeService bestTradesService) {
        this.magicCardmarketService = magicCardmarketService;
        this.allTradesService = allTradesService;
        this.bestTradesService = bestTradesService;
    }

    public void computeBestSellersCommande(List<String> cardLists) {
        WebDriverManager webDriverManager = new WebDriverManager();

        List<Card> cards = magicCardmarketService.retrieveCardsToSale(webDriverManager, cardLists);
        webDriverManager.terminate();

        List<SellerCardsDetail> sellerCardsDetails = allTradesService.buildDeals(cardLists, cards);
        List<Deal> deals = bestTradesService.computeDealsPrice(sellerCardsDetails);
    }
}
