package com.example._younge.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CreateMemberRequest {

    @NotBlank(message = "이름은 필수로 입력해야합니다")
    private String name;

    @NotNull(message = "나이는 필수로 입력해야합니다")
    @Min(value = 0, message = "나이는 0이상을 입력해야합니다")
    @Max(value = 200, message = "나이는 200이하로 입력해야합니다")
    private Integer age;

    @NotBlank(message = "mbti는 필수로 입력해야합니다")
    private String mbti;
}