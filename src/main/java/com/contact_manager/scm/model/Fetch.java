package com.contact_manager.scm.model;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Log4j2
public class Fetch {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken) {
            var oauth2 = (OAuth2AuthenticationToken) authentication;
            var user = (OAuth2User) authentication.getPrincipal();
            if (oauth2.getAuthorizedClientRegistrationId().equalsIgnoreCase("google")) {
                log.info("google");
                return user.getAttribute("email") != null ? user.getAttribute("email").toString() : null;
            } else {
                log.info("github");
                return user.getAttribute("email") != null ? user.getAttribute("email").toString()
                        : (user.getAttribute("login") != null ? user.getAttribute("login").toString()+"@gmail.com" : null);
            }
        } else {
            return authentication.getName();
        }
    }
}
