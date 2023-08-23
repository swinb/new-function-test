package kr.codesquad.secondhand.oauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OAuthController {

	@GetMapping("/api/members/sign-in/{provider}")
	public ResponseEntity<String> login(@PathVariable String provider, @RequestParam String authCode){
		return null;
	}
}
