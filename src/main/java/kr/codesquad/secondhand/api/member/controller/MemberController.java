package kr.codesquad.secondhand.api.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.codesquad.secondhand.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/api/members/sign-in/{provider}")
	public ResponseEntity<String> oauthLogin(@PathVariable String provider, @RequestParam String code) throws
		Exception {
		memberService.login(provider, code);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
