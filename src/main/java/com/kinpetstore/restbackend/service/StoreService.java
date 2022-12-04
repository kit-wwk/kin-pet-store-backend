package com.kinpetstore.restbackend.service;

import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.service.BaseService;
import com.kinpetstore.restbackend.domain.request.StoreRequest;
import com.kinpetstore.restbackend.entity.Store;

public interface StoreService extends BaseService<Store> {

    Store toPojo(StoreRequest storeRequest) throws Exception;

    BaseResponse<String> updateStore(Long id, StoreRequest storeRequest) throws Exception;
}
