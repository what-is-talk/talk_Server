package com.talk.dto.response;

import com.talk.domain.Member;
import com.talk.domain.MemberPosition;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberDetailResponseDto {
    private final String userImage;
    private final String userName;
    private final LocalDateTime joinedDate;
    private final List<String> userPosition;

    @Builder
    public MemberDetailResponseDto (Member member) {
        userImage = member.getProfileImage();
        userName = member.getName();
        joinedDate = member.getCreatedDate();
        userPosition = new ArrayList<>();
        for (MemberPosition memberPosition : member.getMemberPositions()) {
            userPosition.add(memberPosition.getPosition().getName());
        }
    }
}
