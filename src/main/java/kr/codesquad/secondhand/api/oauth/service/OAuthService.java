package kr.codesquad.secondhand.api.oauth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

	public OAuthTokenResponse requestTokenFromProvider(OAuthProvider oAuthProvider, String authCode){
		return WebClient.create()
			.post()
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(getRequestTokenBody(oAuthProvider,authCode))
			.retrieve()
			.bodyToMono(OAuthTokenResponse.class)
			.block();
	}

	public Map<String, String> getRequestTokenBody(OAuthProvider oAuthProvider, String authCode) {
		return Map.of("code", authCode
			, "grant_type", "authorization_code"
			, "client_id", oAuthProvider.getClientId()
			, "client_secret", oAuthProvider.getClientSecret()
			, "code", authCode);
	}

}
