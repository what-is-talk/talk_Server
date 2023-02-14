package com.talk.dto.response;

import com.talk.domain.Meeting;
import com.talk.domain.Schedule;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleDetailResponseDto {
    private final Long scheduleId;
    private final String title;
    private final String description;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final boolean includingTime;
    private final boolean includingEndDate;
    private final LocalDateTime reminder;
    private final String groupName;

    @Builder
    public ScheduleDetailResponseDto (Schedule schedule, Meeting meeting) {
        this.scheduleId = schedule.getId();
        this.title = schedule.getTitle();
        this.description = schedule.getDescription();
        this.startDate = schedule.getStartDate();
        this.endDate = schedule.getEndDate();
        this.includingTime = schedule.isIncludingTime();
        this.includingEndDate = schedule.isIncludingEndDate();
        this.reminder = schedule.getReminder();
        this.groupName = meeting.getName();
    }

}
