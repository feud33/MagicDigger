package com.magic.digger.feature.bestseller.service.alltrades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class SellerCardsDetail {
    private HashMap<String, List<DealDetailElement>> sellerCardDetail;

    private static final List<DealDetailElement> NO_DEAL_DETAIL_ELEMENTS = null;

    public SellerCardsDetail() {
        sellerCardDetail = new HashMap<>();
    }

    public Stream<String> getSellers() {
        return sellerCardDetail.entrySet()
                .stream()
                .map(element -> element.getKey());
    }

    public void add(String seller, String card, int price) {
        DealDetailElement dealDetailElement = new DealDetailElement(card, price);

        List<DealDetailElement> dealDetailElements = sellerCardDetail.get(seller);
        if (dealDetailElements == NO_DEAL_DETAIL_ELEMENTS) {
            dealDetailElements = new ArrayList<>();
            sellerCardDetail.put(seller, dealDetailElements);
        }
        dealDetailElements.add(dealDetailElement);
    }

    public Stream<DealDetailElement> getCards(String seller) {
        return sellerCardDetail.get(seller)
                .stream();
    }
}
