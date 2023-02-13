package com.talk.dto.response;

import com.talk.domain.Member;
import com.talk.domain.MemberPosition;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberListResponseDto {
    private final Long userId;
    private final String userImage;
    private final String userName;
    private final List<String> userPosition;

    @Builder
    public MemberListResponseDto(Member member) {
        userId = member.getId();
        userImage = member.getProfileImage();
        userName = member.getName();
        userPosition = new ArrayList<>();
        for (MemberPosition memberPosition : member.getMemberPositions()) {
            userPosition.add(memberPosition.getPosition().getName());
        }
    }
}
