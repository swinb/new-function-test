package kr.codesquad.secondhand.oauth.repository;

import java.util.Map;

import kr.codesquad.secondhand.oauth.domain.OAuthProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InMemoryProviderRepository {
	private final Map<String, OAuthProvider> providers;

	public OAuthProvider findByProviderName(String name) {
		return providers.get(name);
	}
}