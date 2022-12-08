package com.kinpetstore.restbackend.controller;

import com.kinpetstore.restbackend.base.controller.BaseController;
import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.exception.RecordNotFoundException;
import com.kinpetstore.restbackend.constant.MessageCode;
import com.kinpetstore.restbackend.constant.Path;
import com.kinpetstore.restbackend.domain.request.StoreRequest;
import com.kinpetstore.restbackend.domain.request.StoreSearchRequest;
import com.kinpetstore.restbackend.domain.response.StoreResponse;
import com.kinpetstore.restbackend.entity.Store;
import com.kinpetstore.restbackend.service.StoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Path.API_PREFIX + Path.VERSION_PREFIX_V1 + Path.MODULE_STORE)
public class StoreController extends BaseController<Store, StoreService> {

    private static final Logger logger = LogManager.getLogger(StoreController.class);
    @Autowired
    StoreService storeService;

    public StoreController(StoreService baseService) {
        super(baseService);
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<String> create(@RequestBody StoreRequest storeRequest, @RequestHeader("Authorization") String bearerToken) throws Exception {
        var store = storeService.toPojo(storeRequest);
        return super.saveOrUpdate(null, store);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<StoreResponse> getDistrict(@PathVariable("id") Long id, Locale locale) throws Exception {
        var store = storeService.findById(id);
        if (!store.isPresent()) {
            throw new RecordNotFoundException(MessageCode.Exception.RecordNotFound);
        }
        return BaseResponse.success(store.get().toResponse(locale));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<StoreResponse>> getList(Locale locale) {
        var stores = storeService.findAll();
        return BaseResponse.success(
                stores.stream()
                        .map(it -> it.toResponse(locale))
                        .collect(Collectors.toList())
        );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse<String> update(@PathVariable("id") Long id, @RequestBody StoreRequest storeRequest, @RequestHeader("Authorization") String bearerToken) throws Exception {
        return storeService.updateStore(id, storeRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse<String> delete(@PathVariable("id") Long id, @RequestHeader("Authorization") String bearerToken) {
        return super.deleteAll(List.of(id));
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<StoreResponse>> search(@RequestBody StoreSearchRequest storeSearchRequest, Locale locale) throws Exception {
        return BaseResponse.success(storeService.searchStore(storeSearchRequest, locale));
    }
}
