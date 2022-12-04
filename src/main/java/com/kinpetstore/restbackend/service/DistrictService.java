package com.kinpetstore.restbackend.service;

import com.kinpetstore.restbackend.base.service.BaseService;
import com.kinpetstore.restbackend.entity.District;

import java.util.List;

public interface DistrictService extends BaseService<District> {
    List<District> findAll();
}
