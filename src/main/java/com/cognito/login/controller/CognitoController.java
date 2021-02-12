package com.cognito.login.controller;

import com.cognito.login.HttpHelper;
import com.cognito.login.config.TokenInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.Logger;

@Controller
public class CognitoController {

    Logger logger = Logger.getLogger(CognitoController.class.getName());

    @Autowired
    private HttpHelper httpHelper;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Object info(ModelAndView mav) {
        logger.info("Info method");

        mav.setViewName("info");
        //mav.addObject("someKey", "someValue");

        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Object home(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication, Principal principal) throws IOException {
        logger.info("Home method");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        //mav.addObject("someKey", "someValue");

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