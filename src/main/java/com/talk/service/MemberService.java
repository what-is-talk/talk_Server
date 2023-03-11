package com.talk.service;

import com.talk.dto.response.MemberDetailResponse;
import com.talk.dto.response.MemberListResponse;

public interface MemberService {
    MemberListResponse getAllMemberListofMeeting(Long meetingId);

    MemberDetailResponse getMemberDetail(Long memberId, Long meetingId);

    void withDrawMember(Long memberId);

    void joinMemberTest(String name, String email);

    MemberListResponse getAllMemberList();
}
