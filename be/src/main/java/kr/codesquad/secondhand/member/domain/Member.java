package kr.codesquad.secondhand.member.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long signInTypeId;
    private String email;
    private String nickname;
    private String profileImgUrl;

    @CreatedDate
    private LocalDateTime createdTime;

    @Builder
    public Member(Long signInTypeId, String email, String nickname, String profileImgUrl) {
        this.signInTypeId = signInTypeId;
        this.email = email;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
    }
}
