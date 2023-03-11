package com.talk.dto.response;

import com.talk.domain.Meeting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InviteCodeResponse {
    private final String groupName;
    private final String inviteCode;

    @Builder
    public InviteCodeResponse(Meeting meeting) {
        groupName = meeting.getName();
        inviteCode = meeting.getInviteCode();
    }
}
