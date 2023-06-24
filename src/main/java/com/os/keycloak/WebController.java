package com.os.keycloak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

@Controller
public class WebController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	@GetMapping(path = "/")
	public String index(Principal principal, Model model) {
		if (principal != null) {
			model.addAttribute("username", principal.getName());
		}

		return "public";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) throws Exception {
		request.logout();
		return "redirect:/";
	}

	@GetMapping("/user-logout")
	public String userLogout(HttpServletRequest request) throws Exception {
		return "user-logout";
	}

	@GetMapping(path = "/events")
	public String events(OAuth2AuthenticationToken authentication, Principal principal, Model model) {

		decorateAuth(authentication, principal, model);

		return "events";
	}

	@GetMapping(path = "/agreements")
	public String agreements(OAuth2AuthenticationToken authentication, Principal principal, Model model) {

		decorateAuth(authentication, principal, model);

		return "agreements";
	}

	@GetMapping(path = "/contracts")
	public String contracts(OAuth2AuthenticationToken authentication, Principal principal, Model model) {

		decorateAuth(authentication, principal, model);

		return "contracts";
	}

	private void decorateAuth(OAuth2AuthenticationToken authentication, Principal principal, Model model) {
		OAuth2AuthorizedClient authorizedClient = this.authorizedClientService
				.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

		OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
		model.addAttribute("authtoken", accessToken.getTokenValue());

		model.addAttribute("username", principal.getName());

	}
}
