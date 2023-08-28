package kr.codesquad.secondhand.jwt.repository;

import kr.codesquad.secondhand.jwt.domain.MemberRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<MemberRefreshToken, Long> {

//    boolean existsById(Long memberId);

//    void deleteById(Long memberId);
}
