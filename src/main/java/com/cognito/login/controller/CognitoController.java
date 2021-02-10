package com.cognito.login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class CognitoController {

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Object getName(ModelAndView mav, Authentication authentication, Principal principal) {
        System.out.println(authentication.toString());
        System.out.println(authentication.toString());

        mav.setViewName("info");
        mav.addObject("theObject", "something");
        return mav;
    }

}
