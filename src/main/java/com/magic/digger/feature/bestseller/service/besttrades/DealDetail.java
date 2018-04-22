package com.magic.digger.feature.bestseller.service.besttrades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.magic.digger.feature.bestseller.service.alltrades.DealDetailElement;

public class DealDetail {

    private String seller;
    List<DealDetailElement> dealDetailElements;

    public DealDetail(String seller) {
        this.seller = seller;
        this.dealDetailElements = new ArrayList<>();
    }

    public void addDealDetailElement(String cardName, int price) {
        this.dealDetailElements.add(new DealDetailElement(cardName, price));
    }

    public Iterator<DealDetailElement> getDealDetailElementIterator() {
        return dealDetailElements.iterator();
    }

    public String getSeller() {
        return seller;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        DealDetail that = (DealDetail) o;

        return seller != null ? seller.equals(that.seller) : that.seller == null;
    }

    @Override public int hashCode() {
        return seller != null ? seller.hashCode() : 0;
    }
}
