package com.kinpetstore.restbackend.repository;

import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.Locale;

@Repository
public interface MessageRepository extends BaseRepository<Message> {

    Message findByCodeAndLocale(String key, Locale locale);
}
