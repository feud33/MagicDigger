package com.magic.digger.feature.bestseller.service.alltrades;

public class DealKey {
    private String seller;
    private String card;

    public DealKey(String seller, String card) {
        this.seller = seller;
        this.card = card;
    }

    public String getSeller() {
        return seller;
    }

    public String getCard() {
        return card;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        DealKey dealKey = (DealKey) o;

        if (seller != null ? !seller.equals(dealKey.seller) : dealKey.seller != null)
            return false;
        return card != null ? card.equals(dealKey.card) : dealKey.card == null;
    }

    @Override public int hashCode() {
        int result = seller != null ? seller.hashCode() : 0;
        result = 31 * result + (card != null ? card.hashCode() : 0);
        return result;
    }
}
