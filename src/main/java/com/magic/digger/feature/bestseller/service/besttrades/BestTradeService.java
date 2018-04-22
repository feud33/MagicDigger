package com.magic.digger.feature.bestseller.service.besttrades;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.magic.digger.feature.bestseller.service.alltrades.AvailableCard;
import com.magic.digger.feature.bestseller.service.alltrades.DealDetailElement;

public class BestTradeService {

    private static final AvailableCard UNAVAILABLE_CARD = null;
    public static final int MAIL_FEE = 120;

    private void computeDealsPrice(List<Deal> deals) {
        for (Deal deal : deals) {
            int differentSellersCount = differentSellersCount(deal);

            deal.setPrice(differentSellersCount * MAIL_FEE);

            Iterator<DealDetail> dealIterator = deal.getDealDetailIterator();
            while (dealIterator.hasNext()) {
                DealDetail dealDetail = dealIterator.next();
                addDifferentCardPrice(deal, dealDetail);
            }
        }
    }

    private void addDifferentCardPrice(Deal deal, DealDetail dealDetail) {
        Iterator<DealDetailElement> dealDetailElementIterator = dealDetail.getDealDetailElementIterator();
        while (dealDetailElementIterator.hasNext()) {
            DealDetailElement dealDetailElement = dealDetailElementIterator.next();
            deal.addPrice(dealDetailElement.getPrice());
        }
    }

    private int differentSellersCount(Deal deal) {
        HashSet<String> differentSellers = new HashSet<>();
        Iterator dealIterator = deal.getDealDetailIterator();
        while (dealIterator.hasNext()) {
            DealDetail dealDetail = (DealDetail) dealIterator.next();
            differentSellers.add(dealDetail.getSeller());
        }
        return differentSellers.size();
    }

/*
    private List<Deal> buildDeals(List<String> cards, HashMap<DealKey, AvailableCard> cardMatrix, List<String> sellersSet) {
        List<Deal> deals = new ArrayList<>();
        for (String seller : sellersSet) {
            Deal deal = new Deal();
            for (String card : cards) {
                DealKey dealKey = new DealKey(seller, card);
                AvailableCard availableCard = cardMatrix.get(dealKey);
                deal.addDealDetails(seller, card, availableCard.getPrice());
            }
            deals.add(deal);
        }
        return deals;
    }
*/

}
