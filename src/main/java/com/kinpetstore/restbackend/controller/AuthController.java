package com.kinpetstore.restbackend.controller;

import com.kinpetstore.restbackend.constant.Path;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Path.API_PREFIX + Path.VERSION_PREFIX_V1 + Path.MODULE_AUTHENTICATION)
public class AuthController {
//    @Autowired
//    private AuthConfig authConfig;
//
//    @Autowired
//    private AuthenticationController authenticationController;

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String redirectUri = "http://localhost:8080" + Path.API_PREFIX + Path.VERSION_PREFIX_V1 + Path.MODULE_AUTHENTICATION + "/callback";
//        String authorizeUrl = authenticationController(request, response, redirectUri)
//                .withScope("openid email")
//                .build();
//        response.sendRedirect(authorizeUrl);
//    }
//
//    @GetMapping(value = "/callback")
//    public void callback(HttpServletRequest request, HttpServletResponse response) {
//        Tokens tokens = authenticationController.handle(request, response);
//
//        DecodedJWT jwt = JWT.decode(tokens.getIdToken());
//        TestingAuthenticationToken authToken2 = new TestingAuthenticationToken(jwt.getSubject(),
//                jwt.getToken());
//        authToken2.setAuthenticated(true);
//
//        SecurityContextHolder.getContext().setAuthentication(authToken2);
//        response.sendRedirect(config.getContextPath(request) + "/");
//    }
}
