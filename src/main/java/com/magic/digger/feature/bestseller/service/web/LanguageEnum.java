package com.magic.digger.feature.bestseller.service.web;

public enum LanguageEnum {

    FRENCH("Français"), ENGLISH("Anglais");

    private String language;

    LanguageEnum(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public static LanguageEnum fromString(String language) {
        for (LanguageEnum languageEnum : LanguageEnum.values()) {
            if (languageEnum.language.equals(language)) {
                return languageEnum;
            }
        }
        throw new IllegalStateException();
    }
}
