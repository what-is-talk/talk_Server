package com.talk.service;

import com.talk.domain.*;
import com.talk.dto.response.InviteCodeResponseDto;
import com.talk.dto.response.MemberDetailResponseDto;
import com.talk.dto.response.MemberListResponseDto;
import com.talk.dto.response.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService{

    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;

    @Override
    public MemberListResponseDto getAllMemberListDto(Long meetingId) {
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        if (meetingRepository.findById(meetingId).isPresent()) {
            Meeting meeting = meetingRepository.findById(meetingId).get();
            for (MemberMeeting memberMeeting : meeting.getMember_meetings()) {
                MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                        .member(memberMeeting.getMember())
                        .build();
                memberResponseDtos.add(memberResponseDto);
            }
        }
        return MemberListResponseDto.builder()
                .memberResponseDtos(memberResponseDtos)
                .build();
    }

    @Override
    public MemberDetailResponseDto getMemberDetail(Long memberId, Long meetingId) {
        // TODO : userId가 groupId에 속해있지 않으면 예외처리
        Member member = memberRepository.findById(memberId).get();

        return MemberDetailResponseDto.builder()
                .member(member)
                .build();
    }

    @Override
    public InviteCodeResponseDto getInviteCode(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId).get();

        return InviteCodeResponseDto.builder()
                .meeting(meeting)
                .build();
    }

    @Override
    public void withDrawMember(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        memberRepository.delete(member);
    }
}
