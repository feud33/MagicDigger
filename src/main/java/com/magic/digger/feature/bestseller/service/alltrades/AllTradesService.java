package com.magic.digger.feature.bestseller.service.alltrades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.magic.digger.feature.bestseller.service.web.ForSale;

public class AllTradesService {

    private static final AvailableCard UNAVAILABLE_CARD = null;

    public List<SellerCardsDetail> buildDeals(List<String> cards, List<ForSale> forSales) {
        List<String> sellersSet = buildSellersSet(forSales);
        HashMap<DealKey, AvailableCard> cardMatrix = buildCardsSellersMatrix(forSales);
        return determinePossibleDeals(cards, sellersSet, cardMatrix);
    }

    private List<SellerCardsDetail> determinePossibleDeals(List<String> cards, List<String> sellers,
            HashMap<DealKey, AvailableCard> cardMatrix) {
        List<SellerCardsDetail> possibleDeals = new ArrayList<>();
        boolean matrixBrowseOver = false;
        int sellerCount = sellers.size();
        int cardCount = cards.size();
        int matrixBrowse[] = new int[cardCount];

        int currentCardPos = 0;
        while (!matrixBrowseOver) {
            boolean completeDealDetail = true;
            SellerCardsDetail sellerCardsDetail = new SellerCardsDetail();
            while (currentCardPos < cardCount && completeDealDetail) {
                String card = cards.get(currentCardPos);
                String seller = sellers.get(matrixBrowse[currentCardPos]);
                DealKey dealKey = new DealKey(seller, card);
                AvailableCard availableCard = cardMatrix.get(dealKey);
                if (availableCard == UNAVAILABLE_CARD) {
                    completeDealDetail = false;
                } else {
                    sellerCardsDetail.add(seller, card, availableCard.getPrice());
                }
                currentCardPos++;
            }
            if (completeDealDetail) {
                possibleDeals.add(sellerCardsDetail);
            }

            matrixBrowse[currentCardPos]++;
            if (matrixBrowse[currentCardPos] == sellerCount) {
                matrixBrowse[currentCardPos] = 0;
                currentCardPos++;
                if (currentCardPos == cardCount) {
                    matrixBrowseOver = true;
                } else {
                    matrixBrowse[currentCardPos]++;
                }
            }
        }

        return possibleDeals;
    }

    private List<String> buildSellersSet(List<ForSale> forSales) {
        HashSet<String> sellers = new HashSet<>();
        for (ForSale forSale : forSales) {
            sellers.add(forSale.getSellerName());
        }

        return new ArrayList<String>(sellers);
    }

    private HashMap<DealKey, AvailableCard> buildCardsSellersMatrix(List<ForSale> forSales) {
        HashMap<DealKey, AvailableCard> cardMatrix = new HashMap<>();
        for (ForSale forSale : forSales) {
            DealKey dealKey = new DealKey(forSale.getSellerName(), forSale.getCardName());
            AvailableCard availableCard = new AvailableCard(forSale.getCardName(), forSale.getPrice());
            cardMatrix.put(dealKey, availableCard);
        }
        return cardMatrix;
    }
}
