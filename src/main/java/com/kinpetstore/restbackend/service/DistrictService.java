package com.kinpetstore.restbackend.service;

import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.service.BaseService;
import com.kinpetstore.restbackend.entity.District;

public interface DistrictService extends BaseService<District> {

    BaseResponse<String> updateDistrict(Long id, District district) throws Exception;
}
