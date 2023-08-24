// package kr.codesquad.secondhand.oauth.domain;
//
// import java.lang.reflect.Member;
// import java.util.Arrays;
// import java.util.Map;
//
// public enum OAuthAttributes {
// 	GITHUB("github") {
// 		@Override
// 		public Member of(Map<String, Object> attributes) {
// 			return Member.builder()
// 				.email((String)attributes.get("email"))
// 				.name((String)attributes.get("login"))
// 				.imageUrl((String)attributes.get("avatar_url"))
// 				.build();
// 		}
// 	},
// 	KAKAO("kakao"){
// 		@Override
// 		public Member of(Map<String, Object> attribues){
// 			return Member.builder()
// 				.email((String)attributes.get("email"))
// 				.name((String)attributes.get("nickname"))
// 				.imageUrl((String)attributes.get("picture"))
// 				.build();
// 		}
// 	}
//
// 	private final String providerName;
//
// 	OAuthAttributes(String name) {
// 		this.providerName = name;
// 	}
//
// 	public static Member extract(String providerName, Map<String, Object> attributes) throws Exception {
// 		return Arrays.stream(values())
// 			.filter(provider -> providerName.equals(provider.providerName))
// 			.findFirst()
// 			.orElseThrow(() -> new RuntimeException())
// 			.of(attributes);
// 	}
//
// 	public abstract Member of(Map<String, Object> attributes);
// }

