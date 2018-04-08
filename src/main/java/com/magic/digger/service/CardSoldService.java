package com.magic.digger.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.magic.digger.dao.web.CardSoldDao;

@Service
public class CardSoldService {
    private final CardSoldDao cardSoldDao;

    public CardSoldService(CardSoldDao cardSoldDao) {
        this.cardSoldDao = cardSoldDao;
    }

    public void getCardSolders(WebDriverService driverService, List<String> cardList) {
        cardSoldDao.setup(driverService.getDriver());
        String card = cardList.get(0);
        cardSoldDao.findCard(driverService.getDriver(), card);
        //        cardSoldDao.getCardSolders(driverService.getDriver(), cardList);
    }
}
