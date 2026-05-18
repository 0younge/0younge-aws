package com.example._younge.common.exception;

public class InvalidImageFileException extends RuntimeException {
    public InvalidImageFileException() {
        super("이미지 파일만 업로드할 수 있습니다");
    }
}
