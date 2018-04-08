package com.magic.digger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.magic.digger.service.ForSaleService;
import com.magic.digger.service.WebDriverService;

@Controller
public class BestSellersController {
    private ForSaleService cardSoldService;
    private WebDriverService webDriverService;

    @Autowired
    public BestSellersController(ForSaleService cardSoldService, WebDriverService webDriverService) {
        this.webDriverService = webDriverService;
        this.cardSoldService = cardSoldService;
    }

    public void computeBestSellersCommande(List<String> cardLists) {
        cardSoldService.retrieveForSales(webDriverService, cardLists);
        webDriverService.terminate();
    }
}
