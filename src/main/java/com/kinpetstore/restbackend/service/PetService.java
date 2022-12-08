package com.kinpetstore.restbackend.service;

import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.service.BaseService;
import com.kinpetstore.restbackend.domain.request.PetRequest;
import com.kinpetstore.restbackend.domain.request.PetSearchRequest;
import com.kinpetstore.restbackend.domain.response.PetResponse;
import com.kinpetstore.restbackend.entity.Pet;

import java.util.List;
import java.util.Locale;

public interface PetService extends BaseService<Pet> {

    Pet toPojo(PetRequest petRequest) throws Exception;

    BaseResponse<String> updatePet(Long id, PetRequest petRequest) throws Exception;

    List<PetResponse> searchPet(PetSearchRequest petSearchRequest, Locale locale) throws Exception;
}
