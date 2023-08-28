package kr.codesquad.secondhand.member.repository;

import kr.codesquad.secondhand.member.domain.SignInType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignInTypeRepositoryImpl extends JpaRepository<SignInType, Long> {

    SignInType findByProvider(String provider);
}
