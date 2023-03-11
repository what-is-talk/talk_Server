package com.talk.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleWholeResponse {
    private final Long startYear;
    private final String groupName;
    private final List<ScheduleWholeElementResponse> scheduleDetail;

    @Builder
    public ScheduleWholeResponse(Long startYear, String groupName, List<ScheduleWholeElementResponse> scheduleWholeElementResponses) {
        this.startYear = startYear;
        this.groupName = groupName;
        this.scheduleDetail = scheduleWholeElementResponses;
    }
}
