package kr.codesquad.secondhand.api.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.codesquad.secondhand.api.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
