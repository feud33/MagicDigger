package com.magic.digger.feature.bestseller.service.alltrades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.magic.digger.feature.common.service.cardmarket.Card;

@Service
public class AllTradesService {

    private static final AvailableCard UNAVAILABLE_CARD = null;

    public List<SellerCardsDetail> buildDeals(List<String> cardsName, List<Card> cards) {
        List<String> sellers = buildSellersSet(cards);
        HashMap<DealKey, AvailableCard> cardMatrix = buildCardsSellersMatrix(cards);
        return determinePossibleDeals(cardsName, sellers, cardMatrix);
    }

    private List<SellerCardsDetail> determinePossibleDeals(List<String> cards, List<String> sellers,
            HashMap<DealKey, AvailableCard> cardMatrix) {
        CombinationBrowserService combinationBrowserService = new CombinationBrowserService(cards.size(),
                sellers.size());
        List<SellerCardsDetail> possibleDeals = new ArrayList<>();

        do {
            SellerCardsDetail sellerCardsDetail = new SellerCardsDetail();

            int currentCardPos = 0;
            boolean completeDealDetail = true;
            List<Integer> currentCombinations = combinationBrowserService.getCurrentCombination();
            while (currentCardPos < cards.size()) {
                String card = cards.get(currentCardPos);
                String seller = sellers.get(currentCombinations.get(currentCardPos));
                DealKey dealKey = new DealKey(seller, card);
                AvailableCard availableCard = cardMatrix.get(dealKey);
                if (availableCard == UNAVAILABLE_CARD) {
                    completeDealDetail = false;
                } else {
                    sellerCardsDetail.add(seller, card, availableCard.getPrice());
                }
                currentCardPos++;
            }

            if (cards.size() != 0 && completeDealDetail) {
                possibleDeals.add(sellerCardsDetail);
            }
        } while (combinationBrowserService.next());

        return possibleDeals;
    }

    private List<String> buildSellersSet(List<Card> cards) {
        HashSet<String> sellers = new HashSet<>();
        for (Card card : cards) {
            sellers.add(card.getSellerName());
        }

        return new ArrayList<>(sellers);
    }

    private HashMap<DealKey, AvailableCard> buildCardsSellersMatrix(List<Card> cards) {
        HashMap<DealKey, AvailableCard> cardMatrix = new HashMap<>();
        for (Card card : cards) {
            DealKey dealKey = new DealKey(card.getSellerName(), card.getCardName());
            AvailableCard availableCard = new AvailableCard(card.getCardName(), card.getPrice());
            cardMatrix.put(dealKey, availableCard);
        }
        return cardMatrix;
    }
}
