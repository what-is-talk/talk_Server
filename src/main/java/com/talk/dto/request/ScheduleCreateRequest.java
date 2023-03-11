package com.talk.dto.request;

import com.talk.domain.MeetingRepository;
import com.talk.domain.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleCreateRequest {

    private Long groupId;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean includingTime;
    private boolean includingEndDate;
    private LocalDateTime reminder;

    public Schedule toEntity(MeetingRepository meetingRepository) {
        if (meetingRepository.findById(groupId).isPresent())
            return Schedule.builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .includingTime(includingTime)
                .includingEndDate(includingEndDate)
                .reminder(reminder)
                .meeting(meetingRepository.findById(groupId).get())
                .build();
        return null; // TODO : 예외처리 필요
    }

}
