package kr.codesquad.secondhand.global.config;

import java.util.Map;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.codesquad.secondhand.api.oauth.domain.OAuthProperties;
import kr.codesquad.secondhand.api.oauth.domain.OAuthProvider;
import kr.codesquad.secondhand.api.oauth.repository.InMemoryProviderRepository;
import kr.codesquad.secondhand.api.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableConfigurationProperties(OAuthProperties.class)
@RequiredArgsConstructor
public class OAuthConfig {

	private final OAuthProperties properties;

	@Bean
	public InMemoryProviderRepository inMemoryProviderRepository() {
		Map<String, OAuthProvider> providers = OAuthService.getOauthProviders(properties);
		return new InMemoryProviderRepository(providers);
	}
}