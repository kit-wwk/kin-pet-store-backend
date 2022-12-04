package com.kinpetstore.restbackend.service.impl;

import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.exception.InvalidInputException;
import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.base.service.impl.BaseServiceImpl;
import com.kinpetstore.restbackend.constant.MessageCode;
import com.kinpetstore.restbackend.domain.request.StoreRequest;
import com.kinpetstore.restbackend.entity.Store;
import com.kinpetstore.restbackend.repository.StoreRepository;
import com.kinpetstore.restbackend.service.DistrictService;
import com.kinpetstore.restbackend.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    DistrictService districtService;

    public StoreServiceImpl(BaseRepository<Store> repository) {
        super(repository);
    }

    @Override
    public Store toPojo(StoreRequest storeRequest) throws Exception {
        if (storeRequest.getDistrictId() == null) {
            throw new InvalidInputException(MessageCode.Exception.InvalidInput);
        }
        var district = districtService.findById(storeRequest.getDistrictId()).orElse(null);
        if (Objects.isNull(district)) {
            throw new InvalidInputException(MessageCode.Exception.InvalidInput);
        }
        var storeBuilder = Store.builder()
                .localizedStore(storeRequest.getLocalizedStore())
                .phoneNumber(storeRequest.getPhoneNumber())
                .facebookUrl(storeRequest.getFacebookUrl())
                .instagramUrl(storeRequest.getInstagramUrl())
                .webUrl(storeRequest.getWebUrl())
                .latitude(storeRequest.getLatitude())
                .longitude(storeRequest.getLongitude())
                .district(district)
                .status(storeRequest.getStatus());
        return storeBuilder.build();
    }


    @Override
    public BaseResponse<String> updateStore(Long id, StoreRequest storeRequest) throws Exception {
        var district = districtService.findById(storeRequest.getDistrictId()).orElse(null);
        if (Objects.isNull(district)) {
            throw new InvalidInputException(MessageCode.Exception.InvalidInput);
        }
        try {
            if (id != null) {
                var oldItem = findById(id).orElse(null);
                oldItem.getLocalizedStore().clear();
                oldItem.getLocalizedStore().addAll(storeRequest.getLocalizedStore());
                oldItem.setPhoneNumber(storeRequest.getPhoneNumber());
                oldItem.setFacebookUrl(storeRequest.getFacebookUrl());
                oldItem.setInstagramUrl(storeRequest.getInstagramUrl());
                oldItem.setWebUrl(storeRequest.getWebUrl());
                oldItem.setLatitude(storeRequest.getLatitude());
                oldItem.setLongitude(storeRequest.getLongitude());
                oldItem.setDistrict(district);
                oldItem.setStatus(storeRequest.getStatus());
                save(oldItem);
            } else {
                save(toPojo(storeRequest));
            }
        } catch (DataIntegrityViolationException e) {
            return BaseResponse.error("SQL Error");
        }

        return BaseResponse.success();
    }
}