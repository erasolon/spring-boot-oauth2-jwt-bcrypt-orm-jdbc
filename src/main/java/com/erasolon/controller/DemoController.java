
package com.erasolon.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author erasolon
 * 
 * created on  18/08/2018
 */

@RestController
@RequestMapping("/demo")
public class DemoController {
	
	@GetMapping("/user/")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public String demoUser() {
		return "ROLE_USER/ROLE_ADMIN can see this message";
	}
	
	
	@GetMapping("/admin/")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String demoAdmin() {
		return "ROLE_ADMIN can see this message";
	}

}
