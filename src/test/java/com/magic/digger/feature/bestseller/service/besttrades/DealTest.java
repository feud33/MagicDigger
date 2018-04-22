package com.magic.digger.feature.bestseller.service.besttrades;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.magic.digger.feature.bestseller.service.alltrades.DealDetailElement;

@RunWith(MockitoJUnitRunner.class)
public class DealTest {

    private static final String SELLER1 = "seller 1";
    private static final String SELLER2 = "seller 2";
    private static final String SELLER3 = "seller 3";

    @Test
    public void shouldNotReturnADealDetail() {
        Deal deal = new Deal();

        deal.addDealDetails("seller", "card", 1);

        Iterator it = deal.getDealDetailIterator();
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    public void shouldReturnASingleDetail() {
        Deal deal = new Deal();

        deal.addDealDetails(SELLER1, "card", 1);

        Iterator dealIterator = deal.getDealDetailIterator();
        DealDetail dealDetail = (DealDetail) dealIterator.next();
        assertThat(dealDetail.getSeller()).isEqualTo(SELLER1);
        Iterator dealDetailIterator = dealDetail.getDealDetailElementIterator();
        DealDetailElement dealDetailElement = (DealDetailElement) dealDetailIterator.next();
        assertThat(dealDetailElement.getCardName()).isEqualTo("card");
        assertThat(dealDetailElement.getPrice()).isEqualTo(1);

        assertThat(dealIterator.hasNext()).isFalse();
    }

    @Test
    public void shouldReturnASingleDetailWith2Adds() {
        Deal deal = new Deal();

        deal.addDealDetails(SELLER1, "card", 1);
        deal.addDealDetails(SELLER1, "card2", 2);

        Iterator dealIterator = deal.getDealDetailIterator();
        DealDetail dealDetail = (DealDetail) dealIterator.next();
        assertThat(dealDetail.getSeller()).isEqualTo(SELLER1);
        Iterator dealDetailIterator = dealDetail.getDealDetailElementIterator();
        DealDetailElement dealDetailElement = (DealDetailElement) dealDetailIterator.next();
        assertThat(dealDetailElement.getCardName()).isEqualTo("card");
        assertThat(dealDetailElement.getPrice()).isEqualTo(1);
        dealDetailElement = (DealDetailElement) dealDetailIterator.next();
        assertThat(dealDetailElement.getCardName()).isEqualTo("card2");
        assertThat(dealDetailElement.getPrice()).isEqualTo(2);

        assertThat(dealIterator.hasNext()).isFalse();
    }

    @Test
    public void shouldReturnASingleDetailWith2differentsAdds() {
        Deal deal = new Deal();

        deal.addDealDetails(SELLER1, "card", 1);
        deal.addDealDetails(SELLER1, "card2", 2);
        deal.addDealDetails(SELLER2, "card2", 1);

        Iterator dealIterator = deal.getDealDetailIterator();
        DealDetail dealDetail = (DealDetail) dealIterator.next();
        assertThat(dealDetail.getSeller()).isEqualTo(SELLER1);
        Iterator dealDetailIterator = dealDetail.getDealDetailElementIterator();
        DealDetailElement dealDetailElement = (DealDetailElement) dealDetailIterator.next();
        assertThat(dealDetailElement.getCardName()).isEqualTo("card");
        assertThat(dealDetailElement.getPrice()).isEqualTo(1);
        dealDetailElement = (DealDetailElement) dealDetailIterator.next();
        assertThat(dealDetailElement.getCardName()).isEqualTo("card2");
        assertThat(dealDetailElement.getPrice()).isEqualTo(2);

        dealDetail = (DealDetail) dealIterator.next();
        assertThat(dealDetail.getSeller()).isEqualTo(SELLER2);
        dealDetailIterator = dealDetail.getDealDetailElementIterator();
        dealDetailElement = (DealDetailElement) dealDetailIterator.next();
        assertThat(dealDetailElement.getCardName()).isEqualTo("card2");
        assertThat(dealDetailElement.getPrice()).isEqualTo(1);

        assertThat(dealIterator.hasNext()).isFalse();
    }

}
