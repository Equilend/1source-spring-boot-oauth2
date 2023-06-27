package com.os.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {
	
	@GetMapping(value = "/error")
	public String handleError(HttpServletRequest request, Model model) {

		String errorMsg = "Try your request again or contact support.";
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {

			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.SC_NOT_FOUND) {
				errorMsg = "Requested page is not found. If you followed a link it may be outdated.";
			} else if (statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
				errorMsg = "Looks like an internal error. Try your request again or contact support.";
			}
		}
		
		model.addAttribute("errormsg", errorMsg);
		
		return "error";
	}
}
