package com.talk.service;

import com.talk.dto.response.MemberDetailResponseDto;
import com.talk.dto.response.MemberListResponseDto;

public interface MemberService {
    MemberListResponseDto getAllMemberListofMeeting(Long meetingId);

    MemberDetailResponseDto getMemberDetail(Long memberId, Long meetingId);

    void withDrawMember(Long memberId);

    void joinMemberTest(String name, String email);

    MemberListResponseDto getAllMemberList();
}
