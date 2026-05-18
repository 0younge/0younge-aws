package com.example._younge.domain.controller;

import com.example._younge.domain.dto.CreateMemberRequest;
import com.example._younge.domain.dto.GetMemberResponse;
import com.example._younge.domain.dto.GetProfileImageResponse;
import com.example._younge.domain.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> createMember(@Valid @RequestBody CreateMemberRequest request){

        memberService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMemberResponse> getOneMember(@PathVariable Long id) {

        return ResponseEntity.ok().body(memberService.getOne(id));
    }

    @PostMapping("/{id}/profile-image")
    public ResponseEntity<Void> uploadProfileImage(@PathVariable Long id, @RequestPart("file") MultipartFile file) {

        memberService.uploadImage(id, file);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/profile-image")
    public ResponseEntity<GetProfileImageResponse> getProfileImage(@PathVariable Long id) {

        return ResponseEntity.ok().body(memberService.getProfileImage(id));
    }

}
