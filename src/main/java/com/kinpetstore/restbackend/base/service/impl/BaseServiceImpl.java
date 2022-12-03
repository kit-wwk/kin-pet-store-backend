package com.kinpetstore.restbackend.base.service.impl;

import com.kinpetstore.restbackend.base.entity.BaseEntity;
import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.base.service.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected BaseRepository<T> baseRepository;

    public BaseServiceImpl(BaseRepository<T> repository) {
        this.baseRepository = repository;
    }

    public Optional<T> findById(Long id) {
        return baseRepository.findById(id);
    }

    public List<T> findAllById(List<Long> idList) {
        return baseRepository.findAllById(idList);
    }

    public List<Long> toIdList(List<T> objList) {
        return objList.stream().map(item -> item.getId()).collect(Collectors.toList());
    }

    public void save(T obj) {
        baseRepository.save(obj);
    }

    public void saveAll(List<T> objList) {
        baseRepository.saveAll(objList);
    }

    public void deleteById(Long id) {
        baseRepository.deleteById(id);
    }

    public void deleteAllByIdList(List<Long> idList) {
        baseRepository.deleteAllById(idList);
    }

    public void delete(T obj) {
        if (obj != null) {
            baseRepository.delete(obj);
        }
    }

    public void deleteAll(List<T> objList) {
        baseRepository.deleteAll(objList);
    }

}