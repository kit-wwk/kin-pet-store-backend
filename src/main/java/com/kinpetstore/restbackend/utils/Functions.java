package com.kinpetstore.restbackend.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Functions {

    private static final List<Locale> LOCALES = Arrays.asList(
            Locale.ENGLISH,
            Locale.TRADITIONAL_CHINESE,
            Locale.SIMPLIFIED_CHINESE);

    public static Locale resolveLocale(Locale locale) {
        if (locale == null) {
            return Locale.ENGLISH;
        }

        var localeInHeader = Locale.lookup(Locale.LanguageRange.parse(locale.getLanguage()), LOCALES);
        if (localeInHeader == null) {
            return Locale.ENGLISH;
        }
        return Locale.lookup(Locale.LanguageRange.parse(locale.getLanguage()), LOCALES);
    }
}
