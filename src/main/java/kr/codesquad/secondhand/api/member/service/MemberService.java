package kr.codesquad.secondhand.api.member.service;

import org.springframework.stereotype.Service;

import kr.codesquad.secondhand.api.member.entity.Member;
import kr.codesquad.secondhand.api.member.repository.MemberRepository;
import kr.codesquad.secondhand.api.oauth.domain.OAuthProvider;
import kr.codesquad.secondhand.api.oauth.domain.dto.OAuthTokenResponse;
import kr.codesquad.secondhand.api.oauth.repository.InMemoryProviderRepository;
import kr.codesquad.secondhand.api.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MemberService {

	private final InMemoryProviderRepository inMemoryProviderRepository;
	private final MemberRepository memberRepository;
	private final OAuthService oAuthService;

	public void login(String provider, String authCode) throws Exception {
		OAuthProvider oAuthProvider = inMemoryProviderRepository.findByProviderName(provider);
		OAuthTokenResponse oAuthTokenResponse = oAuthService.requestTokenFromProvider(oAuthProvider, authCode);
		Member member = oAuthService.getMember(provider, oAuthProvider, oAuthTokenResponse);
		System.out.println(member);

	}

}
