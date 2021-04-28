package com.cognito.login.controller;

import com.cognito.login.HttpHelper;
import com.cognito.login.model.ProviderInformation;
import com.cognito.login.model.ProviderType;
import com.cognito.login.model.TokenInformation;
import com.cognito.login.model.UserInfo;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    
    @Autowired
    private HttpHelper httpHelper;
    
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Object info(ModelAndView mav) {
        logger.info("Info method");
        mav.setViewName("info");
        return mav;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object home(Model model,
                       Authentication authentication,
                       @RequestParam(value = "username", required = false) String username,
                       @RequestParam(value = "email", required = false) String email) throws IOException {
        /*logger.info("Home method");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(Objects.toString(email, "Email no provided"));
        userInfo.setUsername(Objects.toString(username, "Username no provided"));

        model.addAttribute("userinfo", userInfo);
        return mav;*/
        logger.info("Home method");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        
        if(authentication != null && authentication.isAuthenticated()) {
            try {
                //El token lo obtuve de aqui pero no pude castearlo
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                Map<String, Object> attributes = ((OAuth2User) authentication.getPrincipal()).getAttributes();
                
                //If it's null means that the login was with users in Cognito User Pool, in other case, comes from
                //a social provider like Google, Facebook, etc.
                ProviderInformation providerInformation = new ProviderInformation();
                String providerImageLogo = "images/cognito.png";
                if(attributes.get("identities") != null) {
                    providerInformation = parseProviderInformation((JSONArray) attributes.get("identities"));
                    providerImageLogo = getProviderPathImage(providerInformation.getProviderType());
                }
                
                UserInfo userInfo = new UserInfo();
                userInfo.setUsername(attributes.get("cognito:username").toString());
                userInfo.setEmail(attributes.get("email").toString());
                userInfo.setRol(authentication.getAuthorities().toString());
                userInfo.setProviderInformation(providerInformation);
                userInfo.setImagePath(providerImageLogo);
                
                model.addAttribute("userinfo", userInfo);
                
                logger.info("UserInfo: " + userInfo);
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
            logger.info("Token information ---> " + tokenInformation);
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