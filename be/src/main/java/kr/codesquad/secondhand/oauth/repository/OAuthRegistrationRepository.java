package kr.codesquad.secondhand.oauth.repository;

import java.util.Map;
import kr.codesquad.secondhand.oauth.domain.OAuthRegistration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OAuthRegistrationRepository {

    private final Map<String, OAuthRegistration> registrations;

    public OAuthRegistration findByProviderName(String name) {
        return registrations.get(name);
    }
}
