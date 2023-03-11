package com.talk.service;

import com.talk.domain.Meeting;
import com.talk.dto.request.MeetingCreateRequest;
import com.talk.dto.response.InviteCodeResponse;

import java.util.List;

public interface MeetingService {
    List<Meeting> getMeetingAll();

    void createMeeting(MeetingCreateRequest meetingCreateRequest);

    InviteCodeResponse getInviteCode(Long meetingId);

    void deleteMeeting(Long meetingId);

    void joinMemberMeeting(Long meetingId, Long memberId);

    void deleteMemberMeeting(Long meetingId, Long memberId);

    List<String> getMeetingAllInfo();
}
