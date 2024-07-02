package com.contact_manager.scm.configuration;

import com.contact_manager.scm.entity.Users;
import com.contact_manager.scm.model.Providers;
import com.contact_manager.scm.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Log4j2
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository repo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuthenticationHandler");

        var oauth2 = (OAuth2AuthenticationToken) authentication;

        String authorizedClientRegistrationId = oauth2.getAuthorizedClientRegistrationId();

        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        String email;
        String name;
        String picture;
        String provider;
        String providerId;
        String about;

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
            email = user.getAttribute("email") != null ? user.getAttribute("email").toString() : null;
            name = user.getAttribute("name") != null ? user.getAttribute("name").toString() : null;
            picture = user.getAttribute("picture") != null ? user.getAttribute("picture").toString() : null;
            providerId = user.getName();
            provider = Providers.GOOGLE.toString();
            about = "Logged in through google";

        } else {
            email = user.getAttribute("email") != null ? user.getAttribute("email").toString() : (user.getAttribute("login") != null ? user.getAttribute("login").toString()+"@gmail.com" : null);
            name = user.getAttribute("login") != null ? user.getAttribute("login").toString() : null;
            picture = user.getAttribute("avatar_url") != null ? user.getAttribute("avatar_url").toString() : null;
            providerId = user.getName();
            provider = Providers.GITHUB.toString();
            about = "Logged in through github";
        }

        Users userDb = Users.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .email(email)
                .profilePic(picture)
                .enabled(true)
                .about(about)
                .provider(provider)
                .emailVerified(true)
                .providerId(providerId)
                .password(UUID.randomUUID().toString())
                .build();

        Users exists = repo.findByEmail(email).orElse(null);
        if (exists == null) {
            log.info("User not exists");
            repo.save(userDb);
            log.info("User saved");
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }
}
