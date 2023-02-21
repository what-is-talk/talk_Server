package com.talk.dto.request;
import com.talk.domain.Meeting;
import com.talk.domain.MemberRepository;
import lombok.Getter;

@Getter
public class MeetingCreateRequest {
    private String name;
    private String groupImageUrl;
    private Long leaderId;

    public Meeting toEntity(MemberRepository memberRepository) {
        if (memberRepository.findById(leaderId).isPresent()) {
            return Meeting.builder()
                    .name(name)
                    .groupImageUrl(groupImageUrl)
                    .leader(memberRepository.findById(leaderId).get())
                    .build();
        }
        return null; // TODO : 예외처리 필요
    }
}
