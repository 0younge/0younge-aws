package com.example._younge.common.exception;

public class S3UploadFailedException extends RuntimeException {
    public S3UploadFailedException() {
        super("프로필 이미지 업로드에 실패했습니다");
    }
}
