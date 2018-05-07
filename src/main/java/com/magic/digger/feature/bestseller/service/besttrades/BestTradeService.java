package com.magic.digger.feature.bestseller.service.besttrades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.magic.digger.feature.bestseller.service.alltrades.DealDetailElement;
import com.magic.digger.feature.bestseller.service.alltrades.SellerCardsDetail;

@Service
public class BestTradeService {

    public static final int MAIL_FEE = 120;

    public BestTradeService() {
    }

    public List<Deal> computeDealsPrice(List<SellerCardsDetail> sellerCardsDetails) {
        List<Deal> deals = new ArrayList<>();

        for(SellerCardsDetail sellerCardsDetail : sellerCardsDetails) {
            Deal deal = new Deal();
            deal.setAmount((int)sellerCardsDetail.getSellers().count() * MAIL_FEE);
            Iterator<DealDetail> dealDetailIterator = deal.getDealDetailIterator();
            while( dealDetailIterator.hasNext()) {
                DealDetail dealDetail = dealDetailIterator.next();

                Iterator<DealDetailElement> dealDetailElementIterator = dealDetail.getDealDetailElementIterator();
                while( dealDetailElementIterator.hasNext()) {
                    DealDetailElement dealDetailElement = dealDetailElementIterator.next();
                    deal.addAmount(dealDetailElement.getPrice());
                }
            }
            deals.add(deal);
        }
        return deals;
    }

}
