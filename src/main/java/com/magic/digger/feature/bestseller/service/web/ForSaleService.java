package com.magic.digger.feature.bestseller.service.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.digger.feature.bestseller.dao.web.cardmarket.CMForSale;
import com.magic.digger.feature.bestseller.dao.web.cardmarket.CardMarketDao;

@Service
public class ForSaleService {
    private final CardMarketDao cardMarketDao;
    private final CMForSaleToForSaleMapper cMForSaleToForSaleMapper;

    @Autowired
    public ForSaleService(CardMarketDao cardMarketDao, CMForSaleToForSaleMapper cMForSaleToForSaleMapper) {
        this.cardMarketDao = cardMarketDao;
        this.cMForSaleToForSaleMapper = cMForSaleToForSaleMapper;
    }

    public List<ForSale> retrieveForSales(WebDriverManager driverService, List<String> cardList) {
        List<ForSale> forSales = new ArrayList<>();

        cardMarketDao.setup(driverService.getDriver());
        for (String card : cardList) {
            cardMarketDao.surfTofindCard(driverService.getDriver(), card);
            cardMarketDao.surfTofilterCard(driverService.getDriver());
            for (CMForSale cmforSale : cardMarketDao.retrieveCardSolder(driverService.getDriver())) {
                try {
                    forSales.add(cMForSaleToForSaleMapper.create(card, cmforSale));
                } catch (IllegalStateException e) {
                    // do nothing
                }
            }
        }
        return forSales;
    }
}
