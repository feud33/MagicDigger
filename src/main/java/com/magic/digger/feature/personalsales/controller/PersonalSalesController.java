package com.magic.digger.feature.personalsales.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.magic.digger.feature.common.service.cardmarket.Card;
import com.magic.digger.feature.common.service.cardmarket.MagicCardmarketService;
import com.magic.digger.feature.common.service.cardmarket.WebDriverManager;
import com.magic.digger.feature.personalsales.service.OutputService;

@Controller public class PersonalSalesController {
    private MagicCardmarketService magicCardmarketService;
    private OutputService outputService;

    @Autowired
    public PersonalSalesController(MagicCardmarketService magicCardmarketService, OutputService outputService) {
        this.magicCardmarketService = magicCardmarketService;
        this.outputService = outputService;
    }

    public void retrivePersonalSales() throws IOException {
        WebDriverManager webDriverManager = new WebDriverManager();
        final String user="feud33";

        List<Card> cards = magicCardmarketService.retrievePersonalSales(webDriverManager, user);
        webDriverManager.terminate();

        outputService.printCsv(cards, user);
    }
}
