package kr.codesquad.secondhand.member.service;

import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import kr.codesquad.secondhand.jwt.domain.Jwt;
import kr.codesquad.secondhand.jwt.service.JwtService;
import kr.codesquad.secondhand.member.domain.Address;
import kr.codesquad.secondhand.member.domain.Member;
import kr.codesquad.secondhand.member.domain.MemberAddress;
import kr.codesquad.secondhand.member.domain.SignInType;
import kr.codesquad.secondhand.member.dto.OAuthSignInResponse;
import kr.codesquad.secondhand.member.repository.AddressRepositoryImpl;
import kr.codesquad.secondhand.member.repository.MemberAddressRepositoryImpl;
import kr.codesquad.secondhand.member.repository.MemberRepository;
import kr.codesquad.secondhand.member.repository.SignInTypeRepositoryImpl;
import kr.codesquad.secondhand.oauth.domain.OAuthProfile;
import kr.codesquad.secondhand.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private static final Long DEFAULT_MEMBER_ADDRESS_ID = 1168064000L;

    private final OAuthService oAuthService;
    private final JwtService jwtService;

    private final SignInTypeRepositoryImpl signInTypeRepository;
    private final MemberRepository memberRepository;
    private final MemberAddressRepositoryImpl memberAddressRepository;
    private final AddressRepositoryImpl addressRepository;

    private Address defaultMemberAddress;

    @PostConstruct
    private void setDefaultMemberAddress() {
        defaultMemberAddress = addressRepository.findById(DEFAULT_MEMBER_ADDRESS_ID).get();
    }

    @Transactional
    public OAuthSignInResponse signInOrSignUp(String providerName, String authorizationCode) {
        OAuthProfile oAuthProfile = oAuthService.requestOauthProfile(providerName, authorizationCode);
        SignInType signInType = signInTypeRepository.findByProvider(providerName); // TODO: 메모리 저장소에 캐싱해두고 사용할 수 있게 개선하는 방법 고민중
        Member member = oAuthProfile.toMember(signInType);

        // DB에 회원 저장 유무 판단
        Optional<Member> findMember = memberRepository.findBySignInTypeIdAndEmail(
                member.getSignInType().getId(),
                member.getEmail()
        );

        if (findMember.isEmpty()) {
            Long memberId = signUp(member);
            return signIn(memberId);
        }
        return signIn(findMember.get().getId());
    }

    private Long signUp(Member member) {
        Long memberId = memberRepository.save(member);
        memberAddressRepository.save(new MemberAddress(member, defaultMemberAddress, true));
        return memberId;
    }

    private OAuthSignInResponse signIn(Long memberId) {
        Jwt jwt = jwtService.issueJwt(memberId);
        List<MemberAddress> memberAddresses = memberAddressRepository.findAllByMemberId(memberId);
        return OAuthSignInResponse.of(jwt, memberAddresses);
    }

}
