package com.os.keycloak;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private HttpSession httpSession;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		
		OAuth2LoginAuthenticationToken authentication = (OAuth2LoginAuthenticationToken)event.getAuthentication();

		OAuth2AccessToken accessToken = authentication.getAccessToken();
		
		httpSession.setAttribute("auth_token", accessToken.getTokenValue());
	}
}