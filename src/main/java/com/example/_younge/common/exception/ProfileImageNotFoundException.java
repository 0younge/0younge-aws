package com.example._younge.common.exception;

public class ProfileImageNotFoundException extends RuntimeException {
    public ProfileImageNotFoundException(Long id) {
        super("프로필 이미지가 존재하지 않습니다 memberId = " + id);
    }
}
