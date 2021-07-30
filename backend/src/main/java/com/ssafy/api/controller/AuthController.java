package com.ssafy.api.controller;

import com.ssafy.api.service.AuthService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Api(value = "인증 API", tags = {"Auth."})
@RestController
//@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	AuthService authService;

	@Autowired
	HttpSession session;

	@RequestMapping("/login/oauth2")
	public String returnCode() {
		session.getAttribute("user");
		return "/";
	}


	/*


	 */
}
