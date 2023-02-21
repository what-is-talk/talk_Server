package com.talk.service;

import com.talk.domain.*;
import com.talk.dto.request.MeetingCreateRequest;
import com.talk.dto.response.InviteCodeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;
    private final MemberMeetingRepository memberMeetingRepository;

    @Override
    public List<Meeting> getMeetingAll() {
        return meetingRepository.findAll();
//        List<String> meetingDesc = new ArrayList<>();
//        for (Meeting meeting : meetingRepository.findAll()) {
//            meetingDesc.add(meeting.toString());
//        }
//        return meetingDesc;
    }

    @Override
    public void createMeeting(MeetingCreateRequest meetingCreateRequest) {
        Meeting meeting = meetingRepository.save(meetingCreateRequest.toEntity(memberRepository));
        //이제 모임장을 가입시킨다
        memberMeetingRepository.save(MemberMeeting.builder()
                .meeting(meeting)
                .member(meeting.getLeader())
                .build());
        // TODO : 초대코드 자동생성 해야함
    }

    @Override
    public InviteCodeResponseDto getInviteCode(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId).get();

        return InviteCodeResponseDto.builder()
                .meeting(meeting)
                .build();
    }

    @Override
    public void deleteMeeting(Long meetingId) {
        if (meetingRepository.findById(meetingId).isPresent()) {
            meetingRepository.delete(meetingRepository.findById(meetingId).get());
        }
    }

    @Override
    public void joinMemberMeeting(Long meetingId, Long memberId) {
        // TODO : 중복가입방지
        memberMeetingRepository.save(MemberMeeting.builder()
                        .member(memberRepository.findById(memberId).get())
                        .meeting(meetingRepository.findById(meetingId).get())
                        .build());
    }

    @Override
    public void deleteMemberMeeting(Long meetingId, Long memberId) {
        memberMeetingRepository.delete(memberMeetingRepository.findByMeetingAndMember(meetingRepository.findById(meetingId).get(), memberRepository.findById(memberId).get()));
    }

    @Override
    public List<String> getMeetingAllInfo() {
        List<String> meetingDesc = new ArrayList<>();
        for (Meeting meeting : meetingRepository.findAll()) {
            meetingDesc.add(meeting.toString());
        }
        return meetingDesc;
    }
}
