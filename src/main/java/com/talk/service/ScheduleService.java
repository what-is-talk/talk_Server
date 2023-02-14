package com.talk.service;

import com.talk.dto.request.ScheduleCreateRequestDto;
import com.talk.dto.request.ScheduleUpdateRequestDto;
import com.talk.dto.response.ScheduleDetailResponseDto;
import com.talk.dto.response.ScheduleWholeResponseDto;

public interface ScheduleService {
    ScheduleWholeResponseDto getWholeSchedule(Long meetingId, Long year);

    ScheduleDetailResponseDto getDetailSchedule(Long scheduleId);

    Long makeSchedule(ScheduleCreateRequestDto scheduleCreateRequestDto);

    void updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto);

    String deleteSchedule(Long scheduleId);
}
