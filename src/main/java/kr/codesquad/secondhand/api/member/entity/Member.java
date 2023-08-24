package kr.codesquad.secondhand.api.member.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int signInTypeId;
	private String email;
	private String nickname;
	private String profileImgUrl;
	private Timestamp createTime;

	@Builder
	public Member(Long id, int signInTypeId, String email, String nickname, String profileImgUrl,
		Timestamp createTime) {
		this.id = id;
		this.signInTypeId = signInTypeId;
		this.email = email;
		this.nickname = nickname;
		this.profileImgUrl = profileImgUrl;
		this.createTime = createTime;
	}
}
