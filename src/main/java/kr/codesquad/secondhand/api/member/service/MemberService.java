package kr.codesquad.secondhand.api.member.service;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import kr.codesquad.secondhand.api.member.repository.MemberRepository;
import kr.codesquad.secondhand.api.oauth.domain.OAuthProvider;
import kr.codesquad.secondhand.api.oauth.domain.dto.OAuthTokenResponse;
import kr.codesquad.secondhand.api.oauth.repository.InMemoryProviderRepository;
import kr.codesquad.secondhand.api.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final InMemoryProviderRepository inMemoryProviderRepository;
	private final MemberRepository memberRepository;
	private final OAuthService oAuthService;

	public void login(String provider, String authCode) {
		OAuthProvider oAuthProvider = inMemoryProviderRepository.findByProviderName(provider);
		OAuthTokenResponse oAuthTokenResponse = oAuthService.requestTokenFromProvider(oAuthProvider, authCode);
	}

}
