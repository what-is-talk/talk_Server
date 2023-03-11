package com.talk.service;

import com.talk.domain.*;
import com.talk.domain.enumpack.AuthProvider;
import com.talk.domain.enumpack.Color;
import com.talk.domain.enumpack.Role;
import com.talk.dto.response.MemberDetailResponse;
import com.talk.dto.response.MemberListResponse;
import com.talk.dto.response.MemberResponse;
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
    public MemberListResponse getAllMemberListofMeeting(Long meetingId) {
        List<MemberResponse> memberResponses = new ArrayList<>();
        if (meetingRepository.findById(meetingId).isPresent()) {
            Meeting meeting = meetingRepository.findById(meetingId).get();
            for (MemberMeeting memberMeeting : meeting.getMemberMeetings()) {
                MemberResponse memberResponse = MemberResponse.builder()
                        .member(memberMeeting.getMember())
                        .build();
                memberResponses.add(memberResponse);
            }
        }
        return MemberListResponse.builder()
                .memberResponses(memberResponses)
                .build();
    }

    @Override
    public MemberDetailResponse getMemberDetail(Long memberId, Long meetingId) {
        // TODO : userId가 groupId에 속해있지 않으면 예외처리
        Member member = memberRepository.findById(memberId).get();

        return MemberDetailResponse.builder()
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
    public MemberListResponse getAllMemberList() {
        List<MemberResponse> memberResponses = new ArrayList<>();
        List<Member> members = memberRepository.findAll();
            for (Member member : members) {
                MemberResponse memberResponse = MemberResponse.builder()
                        .member(member)
                        .build();
                memberResponses.add(memberResponse);
            }
        return MemberListResponse.builder()
                .memberResponses(memberResponses)
                .build();
    }
}
