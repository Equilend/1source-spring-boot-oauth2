package com.os.keycloak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
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

    @GetMapping(path = "/events")
    public String events(Principal principal, Model model) {
        
    	model.addAttribute("username", principal.getName());
        
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication != null && authentication.getPrincipal() instanceof DefaultOidcUser) {
    		DefaultOidcUser user = (DefaultOidcUser)authentication.getPrincipal();
    	    model.addAttribute("authtoken", user.getIdToken().getTokenValue());
    	}
	    
        return "events";
    }

    @GetMapping(path = "/agreements")
    public String agreements(Principal principal, Model model) {
        
    	model.addAttribute("username", principal.getName());
        
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication != null && authentication.getPrincipal() instanceof DefaultOidcUser) {
    		DefaultOidcUser user = (DefaultOidcUser)authentication.getPrincipal();
    	    model.addAttribute("authtoken", user.getIdToken().getTokenValue());
    	}
	    
        return "agreements";
    }

    @GetMapping(path = "/contracts")
    public String contracts(Principal principal, Model model) {
        
    	model.addAttribute("username", principal.getName());
        
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication != null && authentication.getPrincipal() instanceof DefaultOidcUser) {
    		DefaultOidcUser user = (DefaultOidcUser)authentication.getPrincipal();
    	    model.addAttribute("authtoken", user.getIdToken().getTokenValue());
    	}
	    
        return "contracts";
    }

}
