package com.cognito.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //For Step two
        http.authorizeRequests()
                .antMatchers("/templates/**")
                .authenticated()
                .and()
                .oauth2Login()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }
}