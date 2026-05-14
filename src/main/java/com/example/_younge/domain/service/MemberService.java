package com.example._younge.domain.service;

import com.example._younge.common.exception.MemberNotFoundException;
import com.example._younge.domain.dto.CreateMemberRequest;
import com.example._younge.domain.dto.GetMemberResponse;
import com.example._younge.domain.entity.Member;
import com.example._younge.domain.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void create(@Valid CreateMemberRequest request) {

        Member member = new Member(request.getName(), request.getAge(), request.getMbti().toUpperCase());

        memberRepository.save(member);
    }

    public GetMemberResponse getOne(Long id) {

        Member member = memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException(id)
        );

        return GetMemberResponse.from(member);
    }
}
