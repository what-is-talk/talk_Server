package com.talk.service;

import com.talk.domain.Meeting;
import com.talk.dto.request.MeetingCreateRequest;
import com.talk.dto.response.InviteCodeResponseDto;

import java.util.List;

public interface MeetingService {
    List<Meeting> getMeetingAll();

    void createMeeting(MeetingCreateRequest meetingCreateRequest);

    InviteCodeResponseDto getInviteCode(Long meetingId);

    void deleteMeeting(Long meetingId);

    void joinMemberMeeting(Long meetingId, Long memberId);

    void deleteMemberMeeting(Long meetingId, Long memberId);

    List<String> getMeetingAllInfo();
}
