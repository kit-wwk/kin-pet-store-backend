package com.kinpetstore.restbackend.controller;

import com.kinpetstore.restbackend.base.controller.BaseController;
import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.exception.RecordNotFoundException;
import com.kinpetstore.restbackend.constant.MessageCode;
import com.kinpetstore.restbackend.constant.Path;
import com.kinpetstore.restbackend.domain.request.PetRequest;
import com.kinpetstore.restbackend.domain.response.PetResponse;
import com.kinpetstore.restbackend.entity.Pet;
import com.kinpetstore.restbackend.service.PetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Path.API_PREFIX + Path.VERSION_PREFIX_V1 + Path.MODULE_PET)
public class PetController extends BaseController<Pet, PetService> {

    private static final Logger logger = LogManager.getLogger(PetController.class);
    @Autowired
    PetService petService;

    public PetController(PetService baseService) {
        super(baseService);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<String> create(@RequestBody PetRequest petRequest) throws Exception {
        var pet = petService.toPojo(petRequest);
        return super.saveOrUpdate(null, pet);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<PetResponse> getDistrict(@PathVariable("id") Long id, Locale locale) throws Exception {
        var pet = petService.findById(id);
        if (!pet.isPresent()) {
            throw new RecordNotFoundException(MessageCode.Exception.RecordNotFound);
        }
        return BaseResponse.success(pet.get().toResponse(locale));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<PetResponse>> getList(Locale locale) {
        var pets = petService.findAll();
        return BaseResponse.success(
                pets.stream()
                        .map(it -> it.toResponse(locale))
                        .collect(Collectors.toList())
        );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse<String> update(@PathVariable("id") Long id, @RequestBody PetRequest petRequest) throws Exception {
        return petService.updatePet(id, petRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse<String> delete(@PathVariable("id") Long id) {
        return super.deleteAll(List.of(id));
    }
}
