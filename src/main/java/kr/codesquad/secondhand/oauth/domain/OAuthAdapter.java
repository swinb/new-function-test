package kr.codesquad.secondhand.oauth.domain;

import java.util.HashMap;
import java.util.Map;

public class OAuthAdapter {
	public static Map<String, OAuthProvider> getOauthProviders(OAuthProperties properties) {
		Map<String, OAuthProvider> oauthProvider = new HashMap<>();

		properties.getClient().forEach(
			(key, value) -> oauthProvider.put(
				key, new OAuthProvider(value, properties.getProvider().get(key))));
		return oauthProvider;
	}
}