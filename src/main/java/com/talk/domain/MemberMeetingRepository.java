package com.talk.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberMeetingRepository extends JpaRepository<MemberMeeting, Long> {

    @Query(value = "select * from member_meeting i where i.member_id = :memberId", nativeQuery = true)
    List<MemberMeeting> findByMemberId(Long memberId);

    @Query(value = "select * from member_meeting i where i.meeting_id = :groupId", nativeQuery = true)
    List<MemberMeeting> findByMeetingId(Long groupId);

    MemberMeeting findByMeetingAndMember(Meeting meeting, Member member);
}
