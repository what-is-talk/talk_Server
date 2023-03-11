package com.talk.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberListResponse {
    private final List<MemberResponse> memberList;

    @Builder
    public MemberListResponse(List<MemberResponse> memberResponses) {
        this.memberList = memberResponses;
    }
}
