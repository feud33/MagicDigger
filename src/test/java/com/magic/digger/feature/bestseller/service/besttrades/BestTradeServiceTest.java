package com.magic.digger.feature.bestseller.service.besttrades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.magic.digger.feature.bestseller.service.web.ForSale;

@RunWith(MockitoJUnitRunner.class)
public class BestTradeServiceTest {

    private static final String MAGIC_BAZAR = "MagicBazar";
    private static final String VENDEUR2 = "Vendeur2";
    private static final String VENDEUR3 = "Vendeur3";

    private static final String EMISSAIRE_DE_DRANA = "Emissaire de Drana";
    private static final String FAUCON_DE_NUIT_VAMPIRE = "Faucon de nuit vampire";
    private static final String GARDIEN_DE_LA_LIGNEE = "Gardien de la lignée";

    List<ForSale> forSales;
    List<String> cardLists;

    @InjectMocks
    private BestTradeService bestTradeService;

    @Before
    public void setup() {
        bestTradeService = new BestTradeService();
        forSales = new ArrayList<>();
        cardLists = new ArrayList<>();
    }
}
