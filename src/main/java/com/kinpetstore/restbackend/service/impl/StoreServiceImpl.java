package com.kinpetstore.restbackend.service.impl;

import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.exception.InvalidInputException;
import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.base.service.impl.BaseServiceImpl;
import com.kinpetstore.restbackend.constant.MessageCode;
import com.kinpetstore.restbackend.domain.request.StoreRequest;
import com.kinpetstore.restbackend.domain.request.StoreSearchRequest;
import com.kinpetstore.restbackend.domain.response.StoreResponse;
import com.kinpetstore.restbackend.entity.District;
import com.kinpetstore.restbackend.entity.Store;
import com.kinpetstore.restbackend.repository.StoreRepository;
import com.kinpetstore.restbackend.service.DistrictService;
import com.kinpetstore.restbackend.service.StoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.lucene.util.SloppyMath.haversinMeters;

@Service
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {
    private static final Logger logger = LogManager.getLogger(StoreServiceImpl.class);

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

    @Override
    public List<StoreResponse> searchStore(StoreSearchRequest storeSearchRequest, Locale locale) throws Exception {
        List<Long> nearbyStoreIds = null;
        if (storeSearchRequest.getLatitude() != null &&
                storeSearchRequest.getLongitude() != null &&
                storeSearchRequest.getDistanceInKm() != null) {
            List<Store> stores = storeRepository.findAll();

            //Have to compare do in DB drive will enhance performance or not.
            nearbyStoreIds = stores.stream().filter(it -> {
                                Double distance = haversinMeters(storeSearchRequest.getLatitude(), storeSearchRequest.getLongitude(),
                                        it.getLatitude(), it.getLongitude());
                                return distance < storeSearchRequest.getDistanceInKm() * 1000;
                            }
                    )
                    .map(it -> it.getId())
                    .collect(Collectors.toList());
        }
        District district = null;
        if (storeSearchRequest.getDistrictId() != null) {
            district = districtService.findById(storeSearchRequest.getDistrictId()).orElse(null);
            if (district == null) {
                return Collections.emptyList();
            }
        }

        List<Store> stores = nearbyStoreIds == null ?
                storeRepository.search(
                        storeSearchRequest.getPhoneNumber(),
                        storeSearchRequest.getFacebookUrl(),
                        storeSearchRequest.getInstagramUrl(),
                        storeSearchRequest.getWebUrl(),
                        district,
                        storeSearchRequest.getStatus())
                :
                storeRepository.searchWithIds(
                        nearbyStoreIds,
                        storeSearchRequest.getPhoneNumber(),
                        storeSearchRequest.getFacebookUrl(),
                        storeSearchRequest.getInstagramUrl(),
                        storeSearchRequest.getWebUrl(),
                        district,
                        storeSearchRequest.getStatus());
        return stores.stream().map(it -> it.toResponse(locale)).collect(Collectors.toList());
    }
}