package com.magic.digger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.magic.digger.dao.web.cardsold.CardSoldDao;
import com.magic.digger.dao.web.cardsold.ForSale;

@Service
public class ForSaleService {
    private final CardSoldDao cardSoldDao;

    public ForSaleService(CardSoldDao cardSoldDao) {
        this.cardSoldDao = cardSoldDao;
    }

    public List<ForSale> retrieveForSales(WebDriverService driverService, List<String> cardList) {
        List<ForSale> forSales = new ArrayList<>();

        cardSoldDao.setup(driverService.getDriver());
        for (String card : cardList) {
            cardSoldDao.findCard(driverService.getDriver(), card);
            cardSoldDao.filterCard(driverService.getDriver());
            for (ForSale forSale : cardSoldDao.retrieveCardSolder(driverService.getDriver(), card)) {
                forSales.add(forSale);
            }
        }
        return forSales;
    }
}
