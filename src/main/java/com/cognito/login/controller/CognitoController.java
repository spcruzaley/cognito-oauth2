package com.cognito.login.controller;

import com.cognito.login.model.UserInfo;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CognitoController {

    Logger logger = Logger.getLogger(CognitoController.class.getName());

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Object info(ModelAndView mav) {
        logger.info("Info method");
        mav.setViewName("info");
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object home(Model model,
                       @RequestParam(value = "username", required = false) String username,
                       @RequestParam(value = "email", required = false) String email, Authentication authentication) throws IOException {
        logger.info("Home method");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");


        UserInfo userInfo = new UserInfo();
        if (authentication != null) {
            OAuth2User user = (OAuth2User) authentication.getPrincipal();
            userInfo.setUsername(user.getAttributes().toString());
            userInfo.setEmail(((OidcUserAuthority)user.getAuthorities().toArray()[0]).getIdToken().getTokenValue());
        }

        userInfo.setEmail(Objects.toString(email, "Email no provided"));
        userInfo.setUsername(Objects.toString(username, "Username no provided"));

        model.addAttribute("userinfo", userInfo);
        return mav;
    }

}
