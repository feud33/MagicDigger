package com.magic.digger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.magic.digger.service.ForSale;
import com.magic.digger.service.ForSaleService;
import com.magic.digger.service.WebDriverManager;

@Controller
public class BestSellersController {
    private ForSaleService cardSoldService;
    private WebDriverManager webDriverManager;

    @Autowired
    public BestSellersController(ForSaleService cardSoldService, WebDriverManager webDriverService) {
        this.webDriverManager = webDriverService;
        this.cardSoldService = cardSoldService;
    }

    public void computeBestSellersCommande(List<String> cardLists) {
        List<ForSale> forSales = cardSoldService.retrieveForSales(webDriverManager, cardLists);
        webDriverManager.terminate();
    }
}
