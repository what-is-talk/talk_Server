package com.talk.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleWholeResponseDto {
    private final Long startYear;
    private final List<ScheduleDetailResponseDto> scheduleDetail;

    @Builder
    public ScheduleWholeResponseDto (Long startYear, List<ScheduleDetailResponseDto> scheduleDetail) {
        this.startYear = startYear;
        this.scheduleDetail = scheduleDetail;
    }
}
