package com.kinpetstore.restbackend.service.impl;

import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.base.service.impl.BaseServiceImpl;
import com.kinpetstore.restbackend.entity.District;
import com.kinpetstore.restbackend.repository.DistrictRepository;
import com.kinpetstore.restbackend.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl extends BaseServiceImpl<District> implements DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    public DistrictServiceImpl(BaseRepository<District> repository) {
        super(repository);
    }

    @Override


    public BaseResponse<String> updateDistrict(Long id, District newItem) throws Exception {
        try {
            if (id != null) {
                District oldItem = findById(id).orElse(null);
                oldItem.setDistrictCode(newItem.getDistrictCode());
                oldItem.setLocalizedDistrict(newItem.getLocalizedDistricts());
                save(oldItem);
            } else {
                save(newItem);
            }
        } catch (DataIntegrityViolationException e) {
            return BaseResponse.error("SQL Error");
        }

        return BaseResponse.success();
    }
}
