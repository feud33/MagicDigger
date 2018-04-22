package com.magic.digger.feature.bestseller.service.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.magic.digger.feature.bestseller.dao.web.cardmarket.CardMarketDao;

@RunWith(MockitoJUnitRunner.class)
public class ForSaleServiceTest {

    @InjectMocks ForSaleService forSaleService;

    @Mock CardMarketDao cardMarketDao;

    @Mock WebDriverManager driverService;

    @Mock CMForSaleToForSaleMapper forSaleMapper;

    @Before public void setup() {
        //        forSaleService = new ForSaleService(cardMarketDao, forSaleMapper);
        //        doNothing().when(cardMarketDao).setup(any());
    }

    @Test public void shouldReturnEmptyListForEmptyCardList() throws Exception {
        List<String> cardList = new ArrayList<>();

        //        List<ForSale> forSales = forSaleService.retrieveForSales(driverService, cardList);

        //        assertThat(forSales).isEmpty();
    }
}
