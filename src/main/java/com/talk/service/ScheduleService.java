package com.talk.service;

import com.talk.dto.request.ScheduleCreateRequest;
import com.talk.dto.request.ScheduleUpdateRequest;
import com.talk.dto.response.ScheduleDetailResponse;
import com.talk.dto.response.ScheduleWholeResponse;

public interface ScheduleService {
    ScheduleWholeResponse getWholeSchedule(Long meetingId, Long year);

    ScheduleDetailResponse getDetailSchedule(Long scheduleId);

    Long makeSchedule(ScheduleCreateRequest scheduleCreateRequest);

    void updateSchedule(ScheduleUpdateRequest scheduleUpdateRequest);

    String deleteSchedule(Long scheduleId);
}
