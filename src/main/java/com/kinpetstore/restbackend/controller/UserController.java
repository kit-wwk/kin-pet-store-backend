package com.kinpetstore.restbackend.controller;

import com.kinpetstore.restbackend.base.controller.BaseController;
import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.config.AuthConfig;
import com.kinpetstore.restbackend.constant.Path;
import com.kinpetstore.restbackend.domain.request.UserRequest;
import com.kinpetstore.restbackend.domain.request.UserSearchRequest;
import com.kinpetstore.restbackend.entity.User;
import com.kinpetstore.restbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("")
public class UserController extends BaseController<User, UserService> {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    AuthConfig authConfig;

    @Autowired
    UserService userService;

    public UserController(UserService baseService) {
        super(baseService);
    }

    @RequestMapping(value = "/login/oauth2/authorize", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<HashMap<String, String>> authorize(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           @RequestParam("code") String code,
                                                           @RequestParam("state") String state) throws Exception {

        if (Strings.isBlank(code) || Objects.isNull(code)) {
            response.sendRedirect("");
        }
        String accessToken = userService.retrieveAccessToken(code);
        User user = userService.retrieveUserInfo(accessToken);
        userService.createUser(user);

        HashMap<String, String> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("message", "Authentication completed. Please copy the access token and paste to Postman. And try the Kin Pet store API.");
        return BaseResponse.success(result);
    }

    @RequestMapping(value = Path.API_PREFIX + Path.VERSION_PREFIX_V1 + Path.MODULE_USER + "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<User> findUser(@PathVariable("id") Long id, @RequestHeader("Authorization") String bearerToken) throws Exception {
        User user = userService.findById(id).orElse(null);
        return BaseResponse.success(user);
    }

    @RequestMapping(value = Path.API_PREFIX + Path.VERSION_PREFIX_V1 + Path.MODULE_USER + "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse<String> update(@PathVariable("id") Long id, @RequestBody UserRequest userRequest, @RequestHeader("Authorization") String bearerToken) throws Exception {
        userService.updateUser(id, userRequest);
        return BaseResponse.success();
    }

    @RequestMapping(value = Path.API_PREFIX + Path.VERSION_PREFIX_V1 + Path.MODULE_USER + "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<User>> getList(@RequestHeader("Authorization") String bearerToken) {
        var users = userService.findAll();
        return BaseResponse.success(users);
    }

    @RequestMapping(value = Path.API_PREFIX + Path.VERSION_PREFIX_V1 + Path.MODULE_USER + "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse<String> delete(@PathVariable("id") Long id, @RequestHeader("Authorization") String bearerToken) {
        return super.deleteAll(List.of(id));
    }

    @RequestMapping(value = Path.API_PREFIX + Path.VERSION_PREFIX_V1 + Path.MODULE_USER + "/search", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<User>> search(@RequestBody UserSearchRequest userSearchRequest) {
        return BaseResponse.success(userService.search(userSearchRequest));
    }
}
