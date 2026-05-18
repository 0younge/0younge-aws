package com.example._younge.domain.service;

import com.example._younge.common.exception.EmptyImageFileException;
import com.example._younge.common.exception.MemberNotFoundException;
import com.example._younge.common.exception.ProfileImageNotFoundException;
import com.example._younge.common.s3.S3Service;
import com.example._younge.domain.dto.CreateMemberRequest;
import com.example._younge.domain.dto.GetMemberResponse;
import com.example._younge.domain.dto.GetProfileImageResponse;
import com.example._younge.domain.entity.Member;
import com.example._younge.domain.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final S3Service s3Service;

    @Transactional
    public void create(@Valid CreateMemberRequest request) {

        Member member = new Member(request.getName(), request.getAge(), request.getMbti().toUpperCase());

        memberRepository.save(member);
    }

    public GetMemberResponse getOne(Long id) {

        Member member = findByIdOrThrow(id);

        return GetMemberResponse.from(member);
    }

    @Transactional
    public void uploadImage(Long id, MultipartFile file) {

        Member member = findByIdOrThrow(id);

        String key = s3Service.uploadProfileImage(id, file);

        member.uploadProfileImage(key);
    }

    public GetProfileImageResponse getProfileImage(Long id) {

        Member member = findByIdOrThrow(id);

        if (member.getProfileImageKey() == null) {
            throw new ProfileImageNotFoundException(id);
        }

        String url = s3Service.createPresignedUrl(member.getProfileImageKey());

        return new GetProfileImageResponse(url);
    }

    private Member findByIdOrThrow(Long memberId) {

        return memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(memberId)
        );
    }
}
