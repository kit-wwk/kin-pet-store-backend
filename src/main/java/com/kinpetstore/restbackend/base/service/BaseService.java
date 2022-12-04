package com.kinpetstore.restbackend.base.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    /* Search Operation */

    List<T> findAll();

    Optional<T> findById(Long id);

    List<T> findAllById(List<Long> idList);

    List<Long> toIdList(List<T> objList);

    /* Modification */
    void save(T obj);

    void saveAll(List<T> objList);

    void deleteById(Long id);

    void deleteAllByIdList(List<Long> idList);

    void delete(T obj);

    void deleteAll(List<T> objList);
}