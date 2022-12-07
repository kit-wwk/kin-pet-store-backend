package com.kinpetstore.restbackend.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Log4j2
@Configuration
public class LocaleResolver extends AcceptHeaderLocaleResolver
        implements WebMvcConfigurer {

    private final Logger logger = LogManager.getLogger(LocaleResolver.class);

    List<Locale> LOCALES = Arrays.asList(
            Locale.ENGLISH,
            Locale.TRADITIONAL_CHINESE,
            Locale.SIMPLIFIED_CHINESE);

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String headerLang = request.getHeader("Accept-Language");
        logger.info("headerLang: {}", headerLang);
        if (headerLang == null || headerLang.isEmpty()) {
            return Locale.ENGLISH;
        }

        var localeInHeader = Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
        if (localeInHeader == null) {
            return Locale.ENGLISH;
        }
        return Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
    }
}
