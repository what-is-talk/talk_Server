package com.talk.service;

import com.talk.dto.request.ScheduleDetailRequestDto;
import com.talk.dto.response.ScheduleDetailResponseDto;
import com.talk.dto.response.ScheduleWholeResponseDto;

public interface ScheduleService {
    ScheduleWholeResponseDto getWholeSchedule(Long meetingId, Long year);

    ScheduleDetailResponseDto getDetailSchedule(Long scheduleId);

    void makeSchedule(ScheduleDetailRequestDto scheduleDetailRequestDto);

//    void updateSchedule(Schedule schedule);
}
