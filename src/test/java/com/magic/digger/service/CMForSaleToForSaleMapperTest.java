package com.magic.digger.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.magic.digger.dao.web.cardmarket.CMForSale;

public class CMForSaleToForSaleMapperTest {

    public static final String MAGIC_BAZAR = "MagicBazar";
    public static final String PALADIN_AFFAME = "Paladin Affamé";
    public static final String ANGLAIS = "Anglais";
    public static final String INVALIDE = "Invalide";
    public static final String FRANÇAIS = "Français";

    @Test
    public void shouldCreateForSaleWithDifferentLanguage() throws Exception {
        CMForSale cmForSaleFrench = new CMForSale(MAGIC_BAZAR, FRANÇAIS, "0,02", "1");
        CMForSale cmForSaleEnglish = new CMForSale(MAGIC_BAZAR, ANGLAIS, "0,02", "1");

        ForSale forSaleFrench = CMForSaleToForSaleMapper.create(PALADIN_AFFAME, cmForSaleFrench);
        ForSale forSaleEnglish = CMForSaleToForSaleMapper.create(PALADIN_AFFAME, cmForSaleEnglish);

        assertThat(forSaleFrench.getSellerName()).isEqualTo(MAGIC_BAZAR);
        assertThat(forSaleFrench.getCardName()).isEqualTo(PALADIN_AFFAME);
        assertThat(forSaleFrench.getLanguage()).isEqualTo(LanguageEnum.FRENCH);
        assertThat(forSaleFrench.getPrice()).isEqualTo(2);
        assertThat(forSaleFrench.getQuantity()).isEqualTo(1);

        assertThat(forSaleEnglish.getLanguage()).isEqualTo(LanguageEnum.ENGLISH);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionInvalidLanguage() throws Exception {
        CMForSale cmForSaleFrench = new CMForSale(MAGIC_BAZAR, INVALIDE, "0,02", "bad quantity");

        CMForSaleToForSaleMapper.create(PALADIN_AFFAME, cmForSaleFrench);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionInvalidPrice() throws Exception {
        CMForSale cmForSaleFrench = new CMForSale(MAGIC_BAZAR, ANGLAIS, "bad price", "1");

        CMForSaleToForSaleMapper.create(PALADIN_AFFAME, cmForSaleFrench);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionInvalidQuantity() throws Exception {
        CMForSale cmForSaleFrench = new CMForSale(MAGIC_BAZAR, ANGLAIS, "0,02", "1");

        CMForSaleToForSaleMapper.create(PALADIN_AFFAME, cmForSaleFrench);
    }
}
