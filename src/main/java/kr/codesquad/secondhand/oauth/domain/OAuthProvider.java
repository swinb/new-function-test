package kr.codesquad.secondhand.oauth.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthProvider {
	private final String clientId;
	private final String clientSecret;
	private final String redirectUrl;
	private final String tokenUrl;
	private final String userInfoUrl;

	public OAuthProvider(OAuthProperties.Client client, OAuthProperties.Provider provider) {
		this(client.getClientId(), client.getClientSecret(), client.getRedirectUrl(), provider.getTokenUrl(),
			provider.getUserInfoUrl());
	}

	@Builder
	public OAuthProvider(String clientId, String clientSecret, String redirectUrl, String tokenUrl,
		String userInfoUrl) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUrl = redirectUrl;
		this.tokenUrl = tokenUrl;
		this.userInfoUrl = userInfoUrl;
	}
}