package com.kinpetstore.restbackend.service.impl;

import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.exception.InvalidInputException;
import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.base.service.impl.BaseServiceImpl;
import com.kinpetstore.restbackend.constant.MessageCode;
import com.kinpetstore.restbackend.domain.request.PetRequest;
import com.kinpetstore.restbackend.domain.request.PetSearchRequest;
import com.kinpetstore.restbackend.domain.response.PetResponse;
import com.kinpetstore.restbackend.entity.Pet;
import com.kinpetstore.restbackend.entity.Store;
import com.kinpetstore.restbackend.repository.PetRepository;
import com.kinpetstore.restbackend.service.PetService;
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

import static com.kinpetstore.restbackend.constant.DefaultSetting.*;


@Service
public class PetServiceImpl extends BaseServiceImpl<Pet> implements PetService {

    private static final Logger logger = LogManager.getLogger(PetServiceImpl.class);

    @Autowired
    StoreService storeService;
    @Autowired
    PetRepository petRepository;

    public PetServiceImpl(BaseRepository<Pet> repository) {
        super(repository);
    }

    @Override
    public Pet toPojo(PetRequest petRequest) throws Exception {
        if (petRequest.getStoreId() == null) {
            throw new InvalidInputException(MessageCode.Exception.InvalidInput);
        }
        var store = storeService.findById(petRequest.getStoreId()).orElse(null);
        if (Objects.isNull(store)) {
            throw new InvalidInputException(MessageCode.Exception.InvalidInput);
        }

        var petBuilder = Pet.builder()
                .name(petRequest.getName())
                .store(store)
                .image(petRequest.getImage())
                .price(petRequest.getPrice())
                .age(petRequest.getAge())
                .ageGroup(petRequest.getAgeGroup())
                .breed(petRequest.getBreed())
                .petType(petRequest.getPetType())
                .coat(petRequest.getCoat())
                .gender(petRequest.getGender())
                .size(petRequest.getSize())
                .status(petRequest.getStatus());
        return petBuilder.build();
    }


    @Override
    public BaseResponse<String> updatePet(Long id, PetRequest petRequest) throws Exception {
        var store = storeService.findById(petRequest.getStoreId()).orElse(null);
        if (Objects.isNull(store)) {
            throw new InvalidInputException(MessageCode.Exception.InvalidInput);
        }
        try {
            if (id != null) {
                var oldItem = findById(id).orElse(null);
                oldItem.setName(petRequest.getName());
                oldItem.setStore(store);
                oldItem.setImage(petRequest.getImage());
                oldItem.setPrice(petRequest.getPrice());
                oldItem.setAge(petRequest.getAge());
                oldItem.setAgeGroup(petRequest.getAgeGroup());
                oldItem.setBreed(petRequest.getBreed());
                oldItem.setPetType(petRequest.getPetType());
                oldItem.setCoat(petRequest.getCoat());
                oldItem.setGender(petRequest.getGender());
                oldItem.setSize(petRequest.getSize());
                oldItem.setStatus(petRequest.getStatus());
                save(oldItem);
            } else {
                save(toPojo(petRequest));
            }
        } catch (DataIntegrityViolationException e) {
            return BaseResponse.error("SQL Error");
        }

        return BaseResponse.success();
    }

    @Override
    public List<PetResponse> searchPet(PetSearchRequest petSearchRequest, Locale locale) throws Exception {
        Double minPrice = petSearchRequest.getMinPrice() == null
                ? DEFAULT_MIN_PET_PRICE
                : petSearchRequest.getMinPrice();
        Double maxPrice = petSearchRequest.getMaxPrice() == null
                ? DEFAULT_MAX_PET_PRICE
                : petSearchRequest.getMaxPrice();
        if (minPrice >= maxPrice) {
            minPrice = DEFAULT_MIN_PET_PRICE;
            maxPrice = DEFAULT_MAX_PET_PRICE;
        }

        Integer minAge = petSearchRequest.getMinAge() == null
                ? DEFAULT_MIN_PET_AGE
                : petSearchRequest.getMinAge();
        Integer maxAge = petSearchRequest.getMaxAge() == null
                ? DEFAULT_MAX_PET_AGE
                : petSearchRequest.getMaxAge();
        if (minAge >= maxAge) {
            minAge = DEFAULT_MIN_PET_AGE;
            maxAge = DEFAULT_MAX_PET_AGE;
        }
        Store store = null;
        if (petSearchRequest.getStoreId() != null) {
            store = storeService.findById(petSearchRequest.getStoreId()).orElse(null);
            if (store == null) {
                return Collections.emptyList();
            }
        }

        List<Pet> pets = petRepository.search(
                petSearchRequest.getName(),
                minPrice,
                maxPrice,
                minAge,
                maxAge,
                petSearchRequest.getAgeGroup(),
                petSearchRequest.getBreed(),
                petSearchRequest.getPetType(),
                petSearchRequest.getCoat(),
                petSearchRequest.getGender(),
                petSearchRequest.getSize(),
                petSearchRequest.getStatus(),
                store
        );
        return pets.stream().map(it -> it.toResponse(locale)).collect(Collectors.toList());
    }
}