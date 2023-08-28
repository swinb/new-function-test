package kr.codesquad.secondhand.api.jwt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserToken {

	@Id
	private Long memberId;
	private String refreshToken;
}
