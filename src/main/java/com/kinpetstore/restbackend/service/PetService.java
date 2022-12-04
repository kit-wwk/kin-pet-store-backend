package com.kinpetstore.restbackend.service;

import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.service.BaseService;
import com.kinpetstore.restbackend.domain.request.PetRequest;
import com.kinpetstore.restbackend.entity.Pet;

public interface PetService extends BaseService<Pet> {

    Pet toPojo(PetRequest petRequest) throws Exception;

    BaseResponse<String> updatePet(Long id, PetRequest petRequest) throws Exception;
}
