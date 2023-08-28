package kr.codesquad.secondhand.member.repository;

import java.util.Optional;
import kr.codesquad.secondhand.member.domain.Member;

public interface MemberRepository {

    Long save(Member member);

    Optional<Member> findBySignInTypeIdAndEmail(Long signInTypeId, String email);

}
