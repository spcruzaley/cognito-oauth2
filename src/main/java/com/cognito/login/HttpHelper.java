package com.cognito.login;

import com.cognito.login.model.TokenInformation;
import java.util.Base64;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpHelper {
    
    public static String ACCESS_TOKEN = "access_token";
    public static String TOKEN_TYPE = "token_type";
    public static String EXPIRES_IN = "expires_in";
    
    @Value("${spring.security.oauth2.client.registration.cognito-client.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.cognito-client.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.cognito-client.redirect-uri-template}")
    private String redirectUri;
    
    @Value("${spring.security.oauth2.client.registration.cognito-client.client-id}")
    private String clientIdForToken;
    @Value("${spring.security.oauth2.client.registration.cognito-client.client-secret}")
    private String clientSecretForToken;
    @Value("${spring.security.oauth2.client.registration.cognito-client.redirect-uri-template}")
    private String redirectUriForToken;
    
    @Value("${spring.security.oauth2.client.provider.cognito.token-uri}")
    private String tokenUri;
    
    public TokenInformation okHttpClient(String code) {
        OkHttpClient client = new OkHttpClient();
        
        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type", "authorization_code")
                .add("code", code)
                .add("client_id", clientIdForToken)
                .add("redirect_uri", redirectUriForToken)
                .build();
        
        String encoding = Base64.getEncoder().encodeToString((clientIdForToken+":"+ clientSecretForToken).getBytes());
        
        Request request = new Request.Builder()
                .url(tokenUri)
                .addHeader("Authorization", "Basic "+encoding)
                .post(requestBody)
                .build();
        
        TokenInformation tokenInformation = new TokenInformation();
        
        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            
            if(body != null) {
                JSONObject json = new JSONObject(body.string());
                
                if(!json.isEmpty()) {
                    tokenInformation.setToken(json.get(ACCESS_TOKEN).toString());
                    tokenInformation.setToken_type(json.get(TOKEN_TYPE).toString());
                    tokenInformation.setExpiration(Integer.parseInt(json.get(EXPIRES_IN).toString()));
                } else {
                    tokenInformation.setError_message("No access token information got");
                }
            }
        } catch (Exception e) {
            tokenInformation.setError_message(e.getMessage());
        }
        
        return tokenInformation;
    }
}