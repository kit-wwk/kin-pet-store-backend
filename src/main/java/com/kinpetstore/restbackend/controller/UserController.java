package com.kinpetstore.restbackend.controller;

import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.config.AuthConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("")
public class UserController {


    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    AuthConfig authConfig;

    @RequestMapping(value = "/login/oauth2/authorize", method = RequestMethod.GET)
    @ResponseBody
    public void authorize(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam("code") String code,
                          @RequestParam("state") String state) throws Exception {
//        var store = storeService.toPojo(storeRequest);
        logger.info("authorize: {}", code);
        logger.info("state: {}", state);
        if (Strings.isBlank(code) || Objects.isNull(code)) {
            response.sendRedirect("");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject requestBody = new JSONObject();
        requestBody.put("code", code);
        requestBody.put("grant_type", "authorization_code");
        requestBody.put("redirect_uri", "http://127.0.0.1:8080/test123");
        requestBody.put("client_id", authConfig.clientId);

        logger.info("requestBody(: {}", requestBody);

        HttpEntity<String> httpRequest = new HttpEntity<String>(requestBody.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, String> result = restTemplate
                .postForObject(authConfig.issuerUri + "oauth/token", httpRequest, HashMap.class);


        logger.info("access_token(: {}", result.get("access_token"));
//
//
//        String accessTokenUrl = "http://localhost:8080/oauth/token/auth0";
//        accessTokenUrl += "?code=" + code;
//        accessTokenUrl += "&grant_type=authorization_code";
//        accessTokenUrl += "&redirect_uri=http://127.0.0.1:8080/test123";

//        RestTemplate restTemplate = new RestTemplate();
//        response = restTemplate.exchange(accessTokenUrl, HttpMethod.POST, request, String.class);

//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest httpRequest = HttpRequest.newBuilder()
//                .uri(URI.create(accessTokenUrl))
//                .POST(HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println("httpResponse ---------" + httpResponse);
//        ResponseEntity<String> responseEntity = null;
//        System.out.println("Access Token Response ---------" + responseEntity.getBody());
//        return BaseResponse.success();
    }

    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<String> code() throws Exception {
//        var store = storeService.toPojo(storeRequest);
        return BaseResponse.success();
    }
}
