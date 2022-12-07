package com.kinpetstore.restbackend.service;

import com.kinpetstore.restbackend.base.service.BaseService;
import com.kinpetstore.restbackend.domain.request.UserRequest;
import com.kinpetstore.restbackend.entity.User;

public interface UserService extends BaseService<User> {

    User retrieveUserInfo(String accessToken) throws Exception;

    String retrieveAccessToken(String code) throws Exception;

    void updateUser(Long id, UserRequest userRequest) throws Exception;

    void createUser(User user) throws Exception;

    void updateUser(Long id, User user) throws Exception;


}
