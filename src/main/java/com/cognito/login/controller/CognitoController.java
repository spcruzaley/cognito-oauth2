package com.cognito.login.controller;

import com.cognito.login.HttpHelper;
import com.cognito.login.config.TokenInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.Logger;

@RestController
public class CognitoController {

    Logger logger = Logger.getLogger(CognitoController.class.getName());

    @Autowired
    private HttpHelper httpHelper;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Object info(ModelAndView mav) {
        logger.info("Info method");
        mav.setViewName("info");

        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object home(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication, Principal principal) throws IOException {
        logger.info("Home method");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");

        return mav;
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public Object code(@RequestParam(value = "code", required = false) String code,
                     HttpServletRequest request, ModelAndView mav) throws IOException {
        logger.info("Home method");
        TokenInformation tokenInformation = null;

        if(code != null) {
            logger.info("Code gotten: " + code);
            logger.info("Getting Token");

            tokenInformation = httpHelper.okHttpClient(code);
            request.getSession().setAttribute("tokenInformation", tokenInformation);
        }

        mav.setViewName("home");
        mav.addObject("tokenInformation", tokenInformation);

        return mav;
    }

}
