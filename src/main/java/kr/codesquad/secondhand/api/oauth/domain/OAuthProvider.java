package kr.codesquad.secondhand.api.oauth.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthProvider {
	private final String clientId;
	private final String clientSecret;
	private final String redirectUri;
	private final String tokenUri;
	private final String userInfoUri;

	public OAuthProvider(OAuthProperties.Client client, OAuthProperties.Provider provider) {
		this(client.getClientId(), client.getClientSecret(), client.getRedirectUri(), provider.getTokenUri(),
			provider.getUserInfoUri());
	}

	@Builder
	public OAuthProvider(String clientId, String clientSecret, String redirectUri, String tokenUri,
		String userInfoUri) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUri = redirectUri;
		this.tokenUri = tokenUri;
		this.userInfoUri = userInfoUri;
	}
}