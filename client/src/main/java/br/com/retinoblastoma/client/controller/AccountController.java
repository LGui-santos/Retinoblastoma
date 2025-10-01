package br.com.retinoblastoma.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.retinoblastoma.model.dto.UserDto;

@Controller
@RequestMapping("/account")
public class AccountController {

	@GetMapping("/register")
	public String getCreateAccountPage(UserDto usuario) {
		return "account/create-account";
	}
	
	@GetMapping("/register-doctor")
	public String getCreateAccountPageDoctor(UserDto usuario) {
		return "account/create-account-doctor";
	}

	@GetMapping("/login-page")
	public String getLoginPage() {
		return "account/login";
	}

	@GetMapping("/password-recovery")
	public String getRecoverPasswordPage() {
		return "account/recover-password";
	}

}