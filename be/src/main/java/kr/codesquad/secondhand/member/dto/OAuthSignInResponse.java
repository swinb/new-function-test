package kr.codesquad.secondhand.member.dto;

import java.util.List;
import java.util.stream.Collectors;
import kr.codesquad.secondhand.jwt.domain.Jwt;
import kr.codesquad.secondhand.member.domain.MemberAddress;
import lombok.Getter;

@Getter
public class OAuthSignInResponse {

    private final Jwt tokens;
    private final List<MemberAddressResponse> addresses;

    public OAuthSignInResponse(Jwt tokens, List<MemberAddressResponse> memberAddresses) {
        this.tokens = tokens;
        this.addresses = memberAddresses;
    }

    public static OAuthSignInResponse of(Jwt jwt, List<MemberAddress> memberAddress) {
        List<MemberAddressResponse> memberAddressResponses =
                memberAddress.stream()
                        .map(MemberAddressResponse::from)
                        .collect(Collectors.toUnmodifiableList());
        return new OAuthSignInResponse(jwt, memberAddressResponses);
    }
}
