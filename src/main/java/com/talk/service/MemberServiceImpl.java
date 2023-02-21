package com.talk.service;

import com.talk.domain.*;
import com.talk.domain.enumpack.AuthProvider;
import com.talk.domain.enumpack.Color;
import com.talk.domain.enumpack.Role;
import com.talk.dto.response.MemberDetailResponseDto;
import com.talk.dto.response.MemberListResponseDto;
import com.talk.dto.response.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService{

    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;

    @Override
    public MemberListResponseDto getAllMemberListofMeeting(Long meetingId) {
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        if (meetingRepository.findById(meetingId).isPresent()) {
            Meeting meeting = meetingRepository.findById(meetingId).get();
            for (MemberMeeting memberMeeting : meeting.getMemberMeetings()) {
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
    public void withDrawMember(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        memberRepository.delete(member);
    }

    @Override
    public void joinMemberTest(String name, String email) {
        Random random = new Random();
        memberRepository.save(Member.builder()
                        .id((long)random.nextInt(100000000))
                .name(name)
                .email(email)
                .profileImage(name + ".jpg")
                .personalColor(Color.BLUE)
                .role(Role.USER)
                .authProvider(AuthProvider.KAKAO)
                .build());
    }

    @Override
    public MemberListResponseDto getAllMemberList() {
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        List<Member> members = memberRepository.findAll();
            for (Member member : members) {
                MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                        .member(member)
                        .build();
                memberResponseDtos.add(memberResponseDto);
            }
        return MemberListResponseDto.builder()
                .memberResponseDtos(memberResponseDtos)
                .build();
    }
}
