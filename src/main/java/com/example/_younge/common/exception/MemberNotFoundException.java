package com.example._younge.common.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super("팀원을 찾을 수 없습니다. id = " + id);
    }
}
