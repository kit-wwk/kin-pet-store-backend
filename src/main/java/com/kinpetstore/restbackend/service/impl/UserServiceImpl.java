package com.kinpetstore.restbackend.service.impl;

import com.kinpetstore.restbackend.base.exception.RecordNotFoundException;
import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.base.service.impl.BaseServiceImpl;
import com.kinpetstore.restbackend.config.AuthConfig;
import com.kinpetstore.restbackend.constant.MessageCode;
import com.kinpetstore.restbackend.domain.request.UserRequest;
import com.kinpetstore.restbackend.entity.User;
import com.kinpetstore.restbackend.repository.UserRepository;
import com.kinpetstore.restbackend.service.UserService;
import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Objects;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    AuthConfig authConfig;
    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(BaseRepository<User> repository) {
        super(repository);
    }

    @Override
    public User retrieveUserInfo(String accessToken) throws Exception {
        logger.info("accessToken: {}", accessToken);
        logger.info("api: {}", authConfig.issuerUri + "userinfo");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        JSONObject requestBody = new JSONObject();
        HttpEntity<String> httpRequest = new HttpEntity<String>(requestBody.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, String> result = restTemplate
                .postForObject(authConfig.issuerUri + "userinfo", httpRequest, HashMap.class);

        User user = User.builder()
                .sub(result.get("sub"))
                .givenName(result.get("given_name"))
                .nickname(result.get("nickname"))
                .name(result.get("name"))
                .picture(result.get("picture"))
                .email(result.get("email"))
                .build();

        return user;
    }

    @Override
    public String retrieveAccessToken(String code) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject requestBody = new JSONObject();
        requestBody.put("code", code);
        requestBody.put("grant_type", "authorization_code");
        requestBody.put("redirect_uri", "http://127.0.0.1:8080/test123");
        requestBody.put("client_id", authConfig.clientId);
        HttpEntity<String> httpRequest = new HttpEntity<String>(requestBody.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, String> result = restTemplate
                .postForObject(authConfig.issuerUri + "oauth/token", httpRequest, HashMap.class);
        return result.get("access_token");
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) throws Exception {
        var user = userRepository.findById(id).orElse(null);
        if (Objects.isNull(user)) {
            throw new RecordNotFoundException(MessageCode.Exception.InvalidInput);
        }
        user.setName(userRequest.getName());
        user.setGivenName(userRequest.getGivenName());
        user.setNickname(userRequest.getNickname());
        user.setPicture(userRequest.getPicture());
        logger.info("user: {}", user);
        userRepository.save(user);
    }

    @Override
    public void createUser(User user) throws Exception {
        User existingUser = userRepository.findBySub(user.getSub()).orElse(null);
        if (existingUser == null) {
            userRepository.save(user);
        }
    }

    @Override
    public void updateUser(Long id, User user) throws Exception {
        User existingUser = userRepository.findBySub(user.getSub()).orElse(null);
        if (existingUser != null) {
            user.setId(existingUser.getId());
            user.setCreateTime(existingUser.getCreateTime());
            user.setLastUpdateTime(existingUser.getLastUpdateTime());
        }
        userRepository.save(user);
    }
}
