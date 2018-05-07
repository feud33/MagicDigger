package com.magic.digger.feature.bestseller.service.besttrades;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.magic.digger.feature.bestseller.service.alltrades.SellerCardsDetail;

@RunWith(MockitoJUnitRunner.class)
public class BestTradeServiceTest {

    private static final String MAGIC_BAZAR = "MagicBazar";
    private static final String VENDEUR2 = "Vendeur2";
    private static final String VENDEUR3 = "Vendeur3";

    private static final String EMISSAIRE_DE_DRANA = "Emissaire de Drana";
    private static final String FAUCON_DE_NUIT_VAMPIRE = "Faucon de nuit vampire";
    private static final String GARDIEN_DE_LA_LIGNEE = "Gardien de la lignée";

    private BestTradeService bestTradeService;

    @Before
    public void setup() {
        bestTradeService=new BestTradeService();
    }

    @Test
    public void should() {
        SellerCardsDetail sellerCardsDetail = new SellerCardsDetail();
        sellerCardsDetail.add(MAGIC_BAZAR, EMISSAIRE_DE_DRANA, 1);
        sellerCardsDetail.add(MAGIC_BAZAR, FAUCON_DE_NUIT_VAMPIRE, 2);
        sellerCardsDetail.add(MAGIC_BAZAR, GARDIEN_DE_LA_LIGNEE, 3);

        List<SellerCardsDetail> sellerCardsDetails = new ArrayList<>();
        sellerCardsDetails.add(sellerCardsDetail);

        List<Deal> deals = bestTradeService.computeDealsPrice(sellerCardsDetails);

        Assertions.assertThat(deals.size()).isEqualTo(1);
        Deal deal = deals.get(0);
        Assertions.assertThat(deal.getAmount()).isEqualTo(6+BestTradeService.MAIL_FEE);
    }
}
