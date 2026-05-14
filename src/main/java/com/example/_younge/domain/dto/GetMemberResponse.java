package com.example._younge.domain.dto;

import com.example._younge.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMemberResponse {

    private final Long id;
    private final String name;
    private final Integer age;
    private final String mbti;

    public static GetMemberResponse from(Member member) {
        return new GetMemberResponse(member.getId(), member.getName(), member.getAge(), member.getMbti());
    }

}
