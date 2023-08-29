package kr.codesquad.secondhand.member.repository;

import kr.codesquad.secondhand.member.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositoryImpl extends JpaRepository<Address, Long> {
}
