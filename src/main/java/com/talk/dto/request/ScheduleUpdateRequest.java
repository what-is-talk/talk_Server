package com.talk.dto.request;

import com.talk.domain.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleUpdateRequest {
    private Long scheduleId;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean includingTime;
    private boolean includingEndDate;
    private LocalDateTime reminder;

    public Schedule toEntity() {
        return Schedule.builder()
                .id(scheduleId)
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .includingTime(includingTime)
                .includingEndDate(includingEndDate)
                .reminder(reminder)
                .build();
    }
}
