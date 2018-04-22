package com.magic.digger.feature.bestseller.service.alltrades;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class SellerCardsDetailTest {

    private static final String MAGIC_BAZAR = "MagicBazar";
    private static final String SELLER2 = "Seller2";

    private static final String EMISSAIRE_DE_DRANA = "Emissaire de Drana";
    private static final String FAUCON_DE_NUIT_VAMPIRE = "Faucon de nuit vampire";
    private static final String GARDIEN_DE_LA_LIGNEE = "Gardien de la lignée";

    @Test
    public void shouldReturnNoSellers() {
        SellerCardsDetail sellerCardsDetail = new SellerCardsDetail();

        Stream<String> sellers = sellerCardsDetail.getSellers();

        assertThat(sellers.count()).isEqualTo(0);
    }

    @Test
    public void shouldReturn1SellerWithOneAdd() {
        SellerCardsDetail sellerCardsDetail = new SellerCardsDetail();
        sellerCardsDetail.add(MAGIC_BAZAR, EMISSAIRE_DE_DRANA, 1);

        List<String> sellers = sellerCardsDetail.getSellers().sorted().collect(Collectors.toList());
        List<DealDetailElement> dealDetailElements = sellerCardsDetail.getCards(MAGIC_BAZAR).sorted()
                .collect(Collectors.toList());

        assertThat(sellers.size()).isEqualTo(1);
        assertThat(sellers.get(0)).isEqualTo(MAGIC_BAZAR);
        assertThat(dealDetailElements.size()).isEqualTo(1);
        assertThat(dealDetailElements.get(0).getPrice()).isEqualTo(1);
        assertThat(dealDetailElements.get(0).getCardName()).isEqualTo(EMISSAIRE_DE_DRANA);
    }

    @Test
    public void shouldReturn1SellerWithTwoAdd() {
        SellerCardsDetail sellerCardsDetail = new SellerCardsDetail();
        sellerCardsDetail.add(MAGIC_BAZAR, EMISSAIRE_DE_DRANA, 1);
        sellerCardsDetail.add(MAGIC_BAZAR, FAUCON_DE_NUIT_VAMPIRE, 2);

        List<String> sellers = sellerCardsDetail.getSellers().sorted().collect(Collectors.toList());
        List<DealDetailElement> dealDetailElements = sellerCardsDetail.getCards(MAGIC_BAZAR)
                .collect(Collectors.toList());

        assertThat(sellers.size()).isEqualTo(1);
        assertThat(sellers.get(0)).isEqualTo(MAGIC_BAZAR);
        assertThat(dealDetailElements.size()).isEqualTo(2);
        assertThat(dealDetailElements.get(0).getPrice()).isEqualTo(1);
        assertThat(dealDetailElements.get(0).getCardName()).isEqualTo(EMISSAIRE_DE_DRANA);
        assertThat(dealDetailElements.get(1).getPrice()).isEqualTo(2);
        assertThat(dealDetailElements.get(1).getCardName()).isEqualTo(FAUCON_DE_NUIT_VAMPIRE);
    }

    @Test
    public void shouldReturn1SellerWithThrewAdd() {
        SellerCardsDetail sellerCardsDetail = new SellerCardsDetail();
        sellerCardsDetail.add(MAGIC_BAZAR, EMISSAIRE_DE_DRANA, 1);
        sellerCardsDetail.add(MAGIC_BAZAR, FAUCON_DE_NUIT_VAMPIRE, 2);
        sellerCardsDetail.add(SELLER2, FAUCON_DE_NUIT_VAMPIRE, 3);

        List<String> sellers = sellerCardsDetail.getSellers().sorted().collect(Collectors.toList());
        List<DealDetailElement> dealDetailElementsSeller1 = sellerCardsDetail.getCards(MAGIC_BAZAR)
                .collect(Collectors.toList());
        List<DealDetailElement> dealDetailElementsSeller2 = sellerCardsDetail.getCards(SELLER2)
                .collect(Collectors.toList());

        assertThat(sellers.size()).isEqualTo(2);
        assertThat(dealDetailElementsSeller1.size()).isEqualTo(2);
        assertThat(dealDetailElementsSeller1.get(0).getCardName()).isEqualTo(EMISSAIRE_DE_DRANA);
        assertThat(dealDetailElementsSeller1.get(0).getPrice()).isEqualTo(1);
        assertThat(dealDetailElementsSeller1.get(1).getCardName()).isEqualTo(FAUCON_DE_NUIT_VAMPIRE);
        assertThat(dealDetailElementsSeller1.get(1).getPrice()).isEqualTo(2);

        assertThat(dealDetailElementsSeller2.get(0).getCardName()).isEqualTo(GARDIEN_DE_LA_LIGNEE);
        assertThat(dealDetailElementsSeller2.get(0).getPrice()).isEqualTo(3);
    }
}
