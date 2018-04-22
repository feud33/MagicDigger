package com.magic.digger.feature.bestseller.service.web;

public class ForSaleService {
/*
    private final CardMarketDao cardMarketDao;
    private final CMForSaleToForSaleMapper forSaleMapper;

    public ForSaleService(CardMarketDao cardSoldDao, CMForSaleToForSaleMapper forSaleMapper) {
        this.cardMarketDao = cardSoldDao;
        this.forSaleMapper = forSaleMapper;
    }

    public List<ForSale> retrieveForSales(WebDriverManager driverService, List<String> cardList) {
        List<ForSale> forSales = new ArrayList<>();

        cardMarketDao.setup(driverService.getDriver());
        for (String card : cardList) {
            cardMarketDao.surfTofindCard(driverService.getDriver(), card);
            cardMarketDao.surfTofilterCard(driverService.getDriver());
            for (CMForSale cmforSale : cardMarketDao.retrieveCardSolder(driverService.getDriver())) {
                try {
                    forSales.add(forSaleMapper.create(card, cmforSale));
                } catch (IllegalStateException e) {
                    // do nothing
                }
            }
        }
        return forSales;
    }
*/
}
