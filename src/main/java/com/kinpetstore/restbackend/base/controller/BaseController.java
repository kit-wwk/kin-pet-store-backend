package com.kinpetstore.restbackend.base.controller;

import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.entity.BaseEntity;
import com.kinpetstore.restbackend.base.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public abstract class BaseController<T extends BaseEntity, Svc extends BaseService<T>> {

    protected Svc baseService;

    @Autowired
    public BaseController(Svc baseService) {
        this.baseService = baseService;
    }

    protected BaseResponse<T> getDetail(Long id) {
        T item = null;
        if (id != null) {
            item = baseService.findById(id).orElse(null);
        }
        return BaseResponse.success(item);
    }

    protected BaseResponse<String> saveOrUpdate(Long id, T newItem) {
        try {
            if (id != null) {
                T oldItem = baseService.findById(id).orElse(null);
                newItem.setId(id); //handle copyProperties entity.id = null
                BeanUtils.copyProperties(newItem, oldItem);
                baseService.save(oldItem);
            } else {
                baseService.save(newItem);
            }
        } catch (DataIntegrityViolationException e) {
            return BaseResponse.error("SQL Error");
        }

        return BaseResponse.success();
    }

    protected BaseResponse<String> deleteAll(List<Long> idList) {
        baseService.deleteAllByIdList(idList);
        return BaseResponse.success();
    }
}
