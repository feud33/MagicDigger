package com.magic.digger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.magic.digger.service.CardSoldService;
import com.magic.digger.service.webdriver.WebDriverService;

@Controller
public class BestSellersController {
    private CardSoldService cardSoldService;
    private WebDriverService webDriverService;

    @Autowired
    public BestSellersController(CardSoldService cardSoldService, WebDriverService webDriverService) {
        this.webDriverService = webDriverService;
        this.cardSoldService = cardSoldService;
    }

    public void computeBestSellersCommande(List<String> cardLists) {
        webDriverService.create();
        cardSoldService.getCardSolders(cardLists);
    }
}
