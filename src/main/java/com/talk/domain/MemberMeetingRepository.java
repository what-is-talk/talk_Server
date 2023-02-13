package com.talk.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberMeetingRepository extends JpaRepository<MemberMeeting, MemberMeetingID> {

    @Query(value = "select i.meeting from MemberMeeting i where i.memberId = :memberId", nativeQuery = true)
    List<Meeting> findByMemberId(Long memberId);

    @Query(value = "select i from MemberMeeting i where i.meetingId = : groupId", nativeQuery = true)
    List<MemberMeeting> findByMeetingId(Long groupId);

}
