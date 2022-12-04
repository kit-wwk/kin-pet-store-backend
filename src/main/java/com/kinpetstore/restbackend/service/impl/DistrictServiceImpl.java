package com.kinpetstore.restbackend.service.impl;

import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.base.service.impl.BaseServiceImpl;
import com.kinpetstore.restbackend.entity.District;
import com.kinpetstore.restbackend.repository.DistrictRepository;
import com.kinpetstore.restbackend.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl extends BaseServiceImpl<District> implements DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    public DistrictServiceImpl(BaseRepository<District> repository) {
        super(repository);
    }

    @Override
    public List<District> findAll() {
        return districtRepository.findAll();
    }
}
