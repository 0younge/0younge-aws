package com.example._younge.common.exception;

public class EmptyImageFileException extends RuntimeException {
    public EmptyImageFileException() {
        super("이미지 파일은 필수입니다");
    }
}
