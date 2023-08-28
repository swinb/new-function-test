package kr.codesquad.secondhand.api.jwt.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Jwt {
	private String refreshToken;
	private String accessToken;

	@Builder
	public Jwt(String refreshToken, String accessToken) {
		this.refreshToken = refreshToken;
		this.accessToken = accessToken;
	}
}
