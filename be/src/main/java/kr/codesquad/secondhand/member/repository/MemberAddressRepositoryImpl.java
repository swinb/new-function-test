package kr.codesquad.secondhand.member.repository;

import java.util.List;
import kr.codesquad.secondhand.member.domain.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAddressRepositoryImpl extends JpaRepository<MemberAddress, Long> {

    List<MemberAddress> findAllByMemberId(Long memberId);
}
