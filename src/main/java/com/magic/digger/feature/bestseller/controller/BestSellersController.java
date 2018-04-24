package com.magic.digger.feature.bestseller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.magic.digger.feature.bestseller.service.alltrades.AllTradesService;
import com.magic.digger.feature.bestseller.service.alltrades.SellerCardsDetail;
import com.magic.digger.feature.bestseller.service.web.ForSale;
import com.magic.digger.feature.bestseller.service.web.ForSaleService;
import com.magic.digger.feature.bestseller.service.web.WebDriverManager;

@Controller public class BestSellersController {
    private ForSaleService forSaleService;
    private AllTradesService allTradesService;

    @Autowired
    public BestSellersController(ForSaleService forSaleService, AllTradesService allTradesService) {
        this.forSaleService = forSaleService;
        this.allTradesService = allTradesService;
    }

    public void computeBestSellersCommande(List<String> cardLists) {
        WebDriverManager webDriverManager = new WebDriverManager();

        List<ForSale> forSales = forSaleService.retrieveForSales(webDriverManager, cardLists);
        webDriverManager.terminate();

        List<SellerCardsDetail> sellerCardsDetails = allTradesService.buildDeals(cardLists, forSales);
    }
}
