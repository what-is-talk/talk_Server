package com.talk.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberListResponseDto {
    private final List<MemberResponseDto> groupList;

    @Builder
    public MemberListResponseDto (List<MemberResponseDto> memberResponseDtos) {
        this.groupList = memberResponseDtos;
    }
}
