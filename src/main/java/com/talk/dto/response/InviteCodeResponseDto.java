package com.talk.dto.response;

import com.talk.domain.Meeting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InviteCodeResponseDto {
    private final String groupName;
    private final String inviteCode;

    @Builder
    public InviteCodeResponseDto (Meeting meeting) {
        groupName = meeting.getName();
        inviteCode = meeting.getInvite_code();
    }
}
