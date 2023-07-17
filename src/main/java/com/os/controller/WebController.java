package com.os.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.os.api.SearchParty;
import com.os.util.LedgerPartyRepository;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	RestTemplate restTemplate;

//	@Autowired
//	private OAuth2AuthorizedClientService authorizedClientService;

	@Autowired
	private LedgerPartyRepository ledgerPartyRepository;
		
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

	@GetMapping(path = "/parties")
	public String parties(OAuth2AuthenticationToken authentication, Principal principal, Model model, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {

		decorateAuth(authentication, principal, model);

		return "parties";
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
		
		List<SearchParty> myPartyList = ledgerPartyRepository.getPartiesByUser(principal.getName());

		try {
			model.addAttribute("parties", (new ObjectMapper()).writeValueAsString(myPartyList));
		} catch (JsonProcessingException e) {
			logger.error("Error converting party list", e);
			model.addAttribute("parties", "[]");
		}

		model.addAttribute("username", principal.getName());

	}
}
