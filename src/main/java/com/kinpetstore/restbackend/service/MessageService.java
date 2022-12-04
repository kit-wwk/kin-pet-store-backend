package com.kinpetstore.restbackend.service;

import com.kinpetstore.restbackend.entity.Message;
import com.kinpetstore.restbackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

@Component
public class MessageService extends AbstractMessageSource {

    private final String DEFAULT_LOCALE_CODE = Locale.ENGLISH.toString();
    private final HashMap<MessageKeyWrapper, String> persistentMessageMap = new HashMap<>();
    @Autowired
    MessageRepository messageRepository;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        MessageKeyWrapper key = new MessageKeyWrapper(code, locale);
        if (persistentMessageMap.containsKey(key)) {
            return new MessageFormat(persistentMessageMap.get(key), locale);
        }

        Message message = messageRepository.findByCodeAndLocale(code, locale);
        if (!Objects.isNull(message)) {
            persistentMessageMap.put(key, message.getContent());
            return new MessageFormat(message.getContent(), locale);
        }
        return null;
    }

    private class MessageKeyWrapper {
        private final String key;
        private final Locale locale;

        MessageKeyWrapper(String key, Locale locale) {
            this.key = key;
            this.locale = locale;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof MessageKeyWrapper)) return false;
            MessageKeyWrapper otherKey = (MessageKeyWrapper) other;
            return key == otherKey.key && locale == otherKey.locale;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, locale);
        }
    }
}
