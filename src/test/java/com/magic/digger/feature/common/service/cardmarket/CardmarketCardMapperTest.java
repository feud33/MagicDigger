package com.magic.digger.feature.common.service.cardmarket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.magic.digger.feature.common.dao.web.cardmarket.CardmarketCard;

public class CardmarketCardMapperTest {

    public static final String MAGIC_BAZAR = "MagicBazar";
    public static final String PALADIN_AFFAME = "Paladin Affamé";
    public static final String ANGLAIS = "Anglais";
    public static final String INVALIDE = "Invalide";
    public static final String FRANÇAIS = "Français";

    private CardmarketCardToCardMapper cardmarketCardToCardMapper;

    @Before
    public void setup() {
        cardmarketCardToCardMapper = new CardmarketCardToCardMapper();
    }

    @Test
    public void shouldCreateCardWithDifferentLanguage() throws Exception {
        CardmarketCard cardmarketCardFrench = new CardmarketCard(PALADIN_AFFAME, MAGIC_BAZAR, FRANÇAIS, "0,02", "1");
        CardmarketCard cardmarketCardEnglish = new CardmarketCard(PALADIN_AFFAME, MAGIC_BAZAR, ANGLAIS, "0,02", "1");

        Card cardFrench = cardmarketCardToCardMapper.create(cardmarketCardFrench);
        Card cardEnglish = cardmarketCardToCardMapper.create(cardmarketCardEnglish);

        assertThat(cardFrench.getSellerName()).isEqualTo(MAGIC_BAZAR);
        assertThat(cardFrench.getCardName()).isEqualTo(PALADIN_AFFAME);
        assertThat(cardFrench.getLanguage()).isEqualTo(LanguageEnum.FRENCH);
        assertThat(cardFrench.getPrice()).isEqualTo(2);
        assertThat(cardFrench.getQuantity()).isEqualTo(1);

        assertThat(cardEnglish.getLanguage()).isEqualTo(LanguageEnum.ENGLISH);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionInvalidLanguage() throws Exception {
        CardmarketCard cardmarketCardFrench = new CardmarketCard(PALADIN_AFFAME, MAGIC_BAZAR, INVALIDE, "0,02", "bad quantity");

        cardmarketCardToCardMapper.create(cardmarketCardFrench);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionInvalidPrice() throws Exception {
        CardmarketCard cardmarketCardFrench = new CardmarketCard(PALADIN_AFFAME, MAGIC_BAZAR, ANGLAIS, "bad price", "1");

        cardmarketCardToCardMapper.create(cardmarketCardFrench);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionInvalidQuantity() throws Exception {
        CardmarketCard cardmarketCardFrench = new CardmarketCard(PALADIN_AFFAME, MAGIC_BAZAR, ANGLAIS, "0,02", "a");

        cardmarketCardToCardMapper.create(cardmarketCardFrench);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionNegativeQuantity() throws Exception {
        CardmarketCard cardmarketCardFrench = new CardmarketCard(PALADIN_AFFAME, MAGIC_BAZAR, ANGLAIS, "0,02", "-1");

        cardmarketCardToCardMapper.create(cardmarketCardFrench);
    }
}
