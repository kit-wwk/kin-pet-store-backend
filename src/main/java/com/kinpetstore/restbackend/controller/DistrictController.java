package com.kinpetstore.restbackend.controller;

import com.kinpetstore.restbackend.base.controller.BaseController;
import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.exception.RecordNotFoundException;
import com.kinpetstore.restbackend.constant.MessageCode;
import com.kinpetstore.restbackend.constant.Path;
import com.kinpetstore.restbackend.domain.response.DistrictResponse;
import com.kinpetstore.restbackend.entity.District;
import com.kinpetstore.restbackend.service.DistrictService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Path.API_PREFIX + Path.VERSION_PREFIX_V1 + Path.MODULE_DISTRICT)
public class DistrictController extends BaseController<District, DistrictService> {

    private static final Logger logger = LogManager.getLogger(DistrictController.class);
    @Autowired
    DistrictService districtService;

    public DistrictController(DistrictService baseService) {
        super(baseService);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<String> create(@RequestBody District district) {
        return super.saveOrUpdate(null, district);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<DistrictResponse> getDistrict(@PathVariable("id") Long id, Locale locale) throws Exception {
        var district = districtService.findById(id);
        if (!district.isPresent()) {
            throw new RecordNotFoundException(MessageCode.Exception.RecordNotFound);
        }
        return BaseResponse.success(district.get().toResponse(locale));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<DistrictResponse>> getList(Locale locale) {
        var districts = districtService.findAll();
        return BaseResponse.success(
                districts.stream()
                        .map(it -> it.toResponse(locale))
                        .collect(Collectors.toList())
        );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse<String> update(@PathVariable("id") Long id, @RequestBody District district, @RequestHeader("Authorization") String bearerToken) throws Exception {
        return districtService.updateDistrict(id, district);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse<String> delete(@PathVariable("id") Long id, @RequestHeader("Authorization") String bearerToken) {
        return super.deleteAll(List.of(id));
    }
}