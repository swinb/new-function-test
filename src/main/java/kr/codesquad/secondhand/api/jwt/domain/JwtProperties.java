package kr.codesquad.secondhand.api.jwt.domain;

import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
	private String secretKey;
	private Long accessTokenExpirationTime;
	private Long refreshTokenExpirationTime;
}
