package com.magic.digger.feature.bestseller.service.alltrades;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.magic.digger.feature.bestseller.service.web.ForSale;
import com.magic.digger.feature.bestseller.service.web.LanguageEnum;

@RunWith(MockitoJUnitRunner.class)
public class AllTradeServiceTest {

    private static final String MAGIC_BAZAR = "MagicBazar";
    private static final String VENDEUR2 = "Vendeur2";
    private static final String VENDEUR3 = "Vendeur3";

    private static final String EMISSAIRE_DE_DRANA = "Emissaire de Drana";
    private static final String FAUCON_DE_NUIT_VAMPIRE = "Faucon de nuit vampire";
    private static final String GARDIEN_DE_LA_LIGNEE = "Gardien de la lignée";

    private List<ForSale> forSales;
    private List<String> cardLists;

    @InjectMocks
    private AllTradesService allTradeService;

    @Before
    public void setup() {
        allTradeService = new AllTradesService();
        forSales = new ArrayList<>();
        cardLists = new ArrayList<>();
    }

    @Test
    public void shouldReturnEmptyListForEmptyforSales() {
        List<SellerCardsDetail> deals = allTradeService.buildDeals(cardLists, forSales);

        assertThat(deals).isEmpty();
    }

    @Test
    public void shouldCumputeDealWith3Cards2Sellers() {
        forSales.add(new ForSale(MAGIC_BAZAR, EMISSAIRE_DE_DRANA, LanguageEnum.FRENCH, 100, 1));
        forSales.add(new ForSale(MAGIC_BAZAR, FAUCON_DE_NUIT_VAMPIRE, LanguageEnum.FRENCH, 110, 1));
        forSales.add(new ForSale(MAGIC_BAZAR, GARDIEN_DE_LA_LIGNEE, LanguageEnum.FRENCH, 120, 1));
        forSales.add(new ForSale(VENDEUR2, EMISSAIRE_DE_DRANA, LanguageEnum.FRENCH, 10, 1));
        forSales.add(new ForSale(VENDEUR2, FAUCON_DE_NUIT_VAMPIRE, LanguageEnum.FRENCH, 11, 1));
        forSales.add(new ForSale(VENDEUR2, GARDIEN_DE_LA_LIGNEE, LanguageEnum.FRENCH, 12, 1));

        cardLists.add(EMISSAIRE_DE_DRANA);
        cardLists.add(FAUCON_DE_NUIT_VAMPIRE);
        cardLists.add(GARDIEN_DE_LA_LIGNEE);

        List<SellerCardsDetail> sellerCardsDetails = allTradeService.buildDeals(cardLists, forSales);

        assertThat(sellerCardsDetails.size()).isEqualTo(8);
    }

    @Test
    public void shouldCumputeDealWith3Cards3Sellers() {
        forSales.add(new ForSale(MAGIC_BAZAR, EMISSAIRE_DE_DRANA, LanguageEnum.FRENCH, 100, 1));
        forSales.add(new ForSale(MAGIC_BAZAR, FAUCON_DE_NUIT_VAMPIRE, LanguageEnum.FRENCH, 110, 1));
        forSales.add(new ForSale(MAGIC_BAZAR, GARDIEN_DE_LA_LIGNEE, LanguageEnum.FRENCH, 120, 1));
        forSales.add(new ForSale(VENDEUR2, FAUCON_DE_NUIT_VAMPIRE, LanguageEnum.FRENCH, 11, 1));
        forSales.add(new ForSale(VENDEUR2, GARDIEN_DE_LA_LIGNEE, LanguageEnum.FRENCH, 12, 1));

        cardLists.add(EMISSAIRE_DE_DRANA);
        cardLists.add(FAUCON_DE_NUIT_VAMPIRE);
        cardLists.add(GARDIEN_DE_LA_LIGNEE);

        List<SellerCardsDetail> sellerCardsDetails = allTradeService.buildDeals(cardLists, forSales);

        assertThat(sellerCardsDetails.size()).isEqualTo(4);
    }

    @Test
    public void shouldCumputeDealForPerf() {
        cardLists.add("Carte " + 1);
        cardLists.add("Carte " + 2);
        cardLists.add("Carte " + 3);
        cardLists.add("Carte " + 4);
        cardLists.add("Carte " + 5);
        cardLists.add("Carte " + 6);
        cardLists.add("Carte " + 7);
        cardLists.add("Carte " + 8);
        cardLists.add("Carte " + 9);
        cardLists.add("Carte " + 10);

        int seller = 0;
        int MAX_SELLER = 3;
        while (seller < MAX_SELLER) {
            for (String card : cardLists) {
                forSales.add(new ForSale("Vendeur " + seller, card, LanguageEnum.FRENCH, 100, 1));
            }
            seller++;
        }

        List<SellerCardsDetail> sellerCardsDetails = allTradeService.buildDeals(cardLists, forSales);

        assertThat(sellerCardsDetails.size()).isEqualTo(2047);
    }
}
