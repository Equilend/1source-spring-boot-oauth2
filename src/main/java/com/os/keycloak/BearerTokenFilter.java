package com.os.keycloak;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import com.os.controller.WebController;

@Component
public class BearerTokenFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getUserPrincipal() != null) {
			OAuth2AuthorizedClient authorizedClient = this.authorizedClientService.loadAuthorizedClient("keycloak",
					req.getUserPrincipal().getName());
			if (authorizedClient != null) {
				OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
				if (accessToken != null) {
					req.getSession().setAttribute("auth_token", authorizedClient.getAccessToken().getTokenValue());
					logger.info("BEARER TOKEN EXPIRATION -------------> " + accessToken.getExpiresAt());
				}
			}
		}

		chain.doFilter(request, response);
	}

}