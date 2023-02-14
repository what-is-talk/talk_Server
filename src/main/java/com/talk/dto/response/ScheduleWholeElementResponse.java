package com.talk.dto.response;

import com.talk.domain.Schedule;
import lombok.Builder;

import java.time.LocalDateTime;

public class ScheduleWholeElementResponse {
    private final Long scheduleId;
    private final String title;
    private final String description;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final boolean includingTime;
    private final boolean includingEndDate;
    private final LocalDateTime reminder;

    @Builder
    public ScheduleWholeElementResponse (Schedule schedule) {
        this.scheduleId = schedule.getId();
        this.title = schedule.getTitle();
        this.description = schedule.getDescription();
        this.startDate = schedule.getStartDate();
        this.endDate = schedule.getEndDate();
        this.includingTime = schedule.isIncludingTime();
        this.includingEndDate = schedule.isIncludingEndDate();
        this.reminder = schedule.getReminder();
    }

}
