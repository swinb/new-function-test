package kr.codesquad.secondhand.api.oauth.domain;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.codesquad.secondhand.api.member.entity.Member;

public enum OAuthAttributes {
	KAKAO("kakao") {
		@Override
		public Member of(Map<String, Object> attributes) {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> info = (Map<String, Object>)attributes.get("kakao_account");
			Map<String, Object> profile = (Map<String, Object>)info.get("profile");
			return Member.builder()
				.email((String)info.get("email"))
				.nickname((String)profile.get("nickname"))
				.createTime(new Timestamp(System.currentTimeMillis()))
				.signInTypeId(1) //카카오
				.profileImgUrl((String)profile.get("profile_image_url"))
				.build();
		}
	};

	private final String providerName;

	OAuthAttributes(String name) {
		this.providerName = name;
	}

	public static Member extract(String providerName, Map<String, Object> attributes) throws Exception {
		return Arrays.stream(values())
			.filter(provider -> providerName.equals(provider.providerName))
			.findFirst()
			.orElseThrow(() -> new RuntimeException())
			.of(attributes);
	}

	public abstract Member of(Map<String, Object> attributes) throws IOException;
}

