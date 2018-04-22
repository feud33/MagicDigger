package com.magic.digger.feature.bestseller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.magic.digger.feature.bestseller.service.MonService;
import com.magic.digger.feature.bestseller.service.web.WebDriverManager;

@Controller public class BestSellersController {
    //    private ForSaleService forSaleService;

    @Autowired public BestSellersController() {
    }

/*
    public BestSellersController(ForSaleService forSaleService) {
        this.forSaleService = forSaleService;
    }
*/

    public void computeBestSellersCommande(List<String> cardLists) {
        WebDriverManager webDriverManager = new WebDriverManager();
        //        List<ForSale> forSales = forSaleService.retrieveForSales(webDriverManager, cardLists);
        //        webDriverManager.terminate();

        MonService monService = new MonService();
        monService.print();
        System.out.println("ok");
    }
}
