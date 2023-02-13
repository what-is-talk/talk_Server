package com.talk.service;

import com.talk.dto.response.InviteCodeResponseDto;
import com.talk.dto.response.MemberDetailResponseDto;
import com.talk.dto.response.MemberListResponseDto;

import java.util.List;

public interface MemberService {
    List<MemberListResponseDto> getAllMemberListDto(Long meetingId);

    MemberDetailResponseDto getMemberDetail(Long memberId, Long meetingId);

    InviteCodeResponseDto getInviteCode(Long meetingId);

    void withDrawMember(Long memberId);
}
