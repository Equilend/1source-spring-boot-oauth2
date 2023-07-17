package com.os.keycloak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;

public class AuthTokenConverter extends OAuth2AccessTokenResponseHttpMessageConverter {

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenConverter.class);

	@Override
	protected OAuth2AccessTokenResponse readInternal(Class<? extends OAuth2AccessTokenResponse> clazz,
			HttpInputMessage inputMessage) throws HttpMessageNotReadableException {
		OAuth2AccessTokenResponse tokenResponse = super.readInternal(clazz, inputMessage);
		
		logger.info("TOKEN REFRESH-------------> " + tokenResponse.getAccessToken().getExpiresAt());

		return tokenResponse;
	}

}
