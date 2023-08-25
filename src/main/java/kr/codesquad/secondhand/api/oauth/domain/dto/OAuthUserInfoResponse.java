package kr.codesquad.secondhand.api.oauth.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthUserInfoResponse {
	private String email;
	private String nickname;
	private String profileImgUrl;
}
