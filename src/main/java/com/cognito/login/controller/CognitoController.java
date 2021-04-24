package com.cognito.login.controller;

import com.cognito.login.HttpHelper;
import com.cognito.login.config.TokenInformation;
import com.cognito.login.model.ProviderInformation;
import com.cognito.login.model.ProviderType;
import com.cognito.login.model.UserInfo;
import java.util.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.*;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.*;
import org.springframework.ui.*;
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
                       Authentication authentication, Principal principal, Model model) throws IOException {
        logger.info("Home method");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");

        if(principal != null) {
            try {
                Map<String, Object> attributes = ((OAuth2User) authentication.getPrincipal()).getAttributes();

                //If it's null means that the login was with users in Cognito User Pool, in other case, comes from
                //a social provider like Google, Facebook, etc.
                ProviderInformation providerInformation = new ProviderInformation();
                String providerImageLogo = "images/cognito.png";
                if(attributes.get("identities") != null) {
                    providerInformation = parseProviderInformation((JSONArray) attributes.get("identities"));
                    providerImageLogo = getProviderPathImage(providerInformation.getProviderType());
                }

                List<OidcUserAuthority> authorities = (List<OidcUserAuthority>) authentication.getAuthorities();

                UserInfo userInfo = new UserInfo();
                userInfo.setUsername(attributes.get("cognito:username").toString());
                userInfo.setEmail(attributes.get("email").toString());
                userInfo.setRol(authentication.getAuthorities().toString());
                userInfo.setProviderInformation(providerInformation);
                userInfo.setImagePath(providerImageLogo);
                userInfo.setToken(authorities.get(0).getIdToken().getTokenValue());

                model.addAttribute("userinfo", userInfo);
            } catch (Exception e) {
                logger.warning("Error casting: " + e.getMessage());
            }
        }

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

    /**
     * Parse the information that comes from the Social Provider into ProviderInformation object to be shown
     *
     * @param contents the provider information given
     * @return ProviderInformation
     */
    public ProviderInformation parseProviderInformation(JSONArray contents) {
        ProviderInformation providerInformation = new ProviderInformation();
        if(contents.size() > 0) {
            JSONObject item = (JSONObject) contents.get(0);

            providerInformation.setUserId(item.get("userId").toString());
            providerInformation.setProviderName(item.get("providerName").toString());
            providerInformation.setProviderType(ProviderType.valueOf(item.get("providerType").toString()));
            providerInformation.setIssuer(Objects.toString(item.get("issuer"), ""));
        }

        return providerInformation;
    }

    /**
     * Return the image related to the social provider provided
     *
     * @param providerType the given ProviderType
     * @return String with the image name
     */
    private String getProviderPathImage(ProviderType providerType) {
        switch (providerType) {
            case SAML:
                return "images/saml.png";
            case Google:
                return "images/google.png";
            case Facebook:
                return "images/facebook.png";
            case LoginWithAmazon:
                return "images/amazon.png";
        }

        return "";
    }

}
