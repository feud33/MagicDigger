package com.magic.digger.feature.bestseller.service.besttrades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.magic.digger.feature.bestseller.service.alltrades.DealDetailElement;

public class Deal implements Comparable<Deal> {

    private List<DealDetail> dealDetails;
    private int amount;
    private int sellersCount;

    public Deal() {
        this.dealDetails = new ArrayList<>();
    }

    public void addDealDetails(String seller, String card, int price) {
        DealDetail dealDetail = new DealDetail(seller);
        if (!this.dealDetails.contains(dealDetail)) {
            this.dealDetails.add(dealDetail);
        }
        int dealDetailIndex = this.dealDetails.indexOf(dealDetail);

        DealDetail dealDetailInList = this.dealDetails.get(dealDetailIndex);

        DealDetailElement dealDetailElement = new DealDetailElement(card, price);
        //        dealDetailInList.addDealDetailElement(dealDetailElement);
    }

    public Iterator<DealDetail> getDealDetailIterator() {
        return dealDetails.iterator();
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amountToAdd) {
        this.amount = this.amount + amountToAdd;
    }

    public int getAmount() {
        return amount;
    }

    public int getSellersCount() {
        return sellersCount;
    }

    public void setSellersCount(int sellersCount) {
        this.sellersCount = sellersCount;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Deal deal = (Deal) o;

        return amount == deal.amount;
    }

    @Override public int hashCode() {
        return amount;
    }

    @Override public int compareTo(Deal deal) {
        return ((Integer) amount).compareTo(deal.getAmount());
    }
}
