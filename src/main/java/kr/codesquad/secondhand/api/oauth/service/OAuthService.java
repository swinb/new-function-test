package kr.codesquad.secondhand.api.oauth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import kr.codesquad.secondhand.api.member.entity.Member;
import kr.codesquad.secondhand.api.oauth.domain.OAuthAttributes;
import kr.codesquad.secondhand.api.oauth.domain.OAuthProperties;
import kr.codesquad.secondhand.api.oauth.domain.OAuthProvider;
import kr.codesquad.secondhand.api.oauth.domain.dto.OAuthTokenResponse;

@Service
public class OAuthService {

	public static Map<String, OAuthProvider> getOauthProviders(OAuthProperties properties) {
		Map<String, OAuthProvider> oauthProvider = new HashMap<>();
		
		properties.getClient().forEach(
			(key, value) -> oauthProvider.put(
				key, new OAuthProvider(value, properties.getProvider().get(key))));
		return oauthProvider;
	}

	public OAuthTokenResponse requestTokenFromProvider(OAuthProvider oAuthProvider, String authCode) {
		return WebClient.create()
			.post()
			.uri(oAuthProvider.getTokenUri())
			.body(BodyInserters.fromFormData(getRequestTokenBody(oAuthProvider, authCode)))
			.headers(header -> {
				header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			})
			.retrieve()
			.bodyToMono(OAuthTokenResponse.class)
			.block();

	}

	public MultiValueMap<String, String> getRequestTokenBody(OAuthProvider oAuthProvider, String authCode) {
		MultiValueMap params = new LinkedMultiValueMap();
		params.add("grant_type", "authorization_code");
		params.add("client_id", oAuthProvider.getClientId());
		params.add("redirect_uri", oAuthProvider.getRedirectUri());
		params.add("code", authCode);
		params.add("client_secret", oAuthProvider.getClientSecret());
		return params;
	}

	public Member getMember(String provider, OAuthProvider oAuthProvider, OAuthTokenResponse tokenResponse) throws
		Exception {
		return OAuthAttributes.extract(provider, requestUserInfoFromProvider(oAuthProvider, tokenResponse));
	}

	public Map<String, Object> requestUserInfoFromProvider(OAuthProvider oAuthProvider,
		OAuthTokenResponse tokenResponse) {
		return WebClient.create()
			.get()
			.uri(oAuthProvider.getUserInfoUri())
			.headers(header -> {
				header.setBearerAuth(tokenResponse.getAccessToken());
			})
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
			})
			.block();
	}
}
