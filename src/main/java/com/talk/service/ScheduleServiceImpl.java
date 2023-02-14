package com.talk.service;

import com.talk.domain.Meeting;
import com.talk.domain.MeetingRepository;
import com.talk.domain.Schedule;
import com.talk.domain.ScheduleRepository;
import com.talk.dto.request.ScheduleDetailRequestDto;
import com.talk.dto.response.ScheduleDetailResponseDto;
import com.talk.dto.response.ScheduleWholeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MeetingRepository meetingRepository;

    @Override
    public ScheduleWholeResponseDto getWholeSchedule(Long meetingId, Long year) {
        Long startYear = (year / 10) * 10;
        List<ScheduleDetailResponseDto> scheduleDetailResponseDtos = new ArrayList<>();
        if (meetingRepository.findById(meetingId).isPresent()){ // TODO : 그룹이 없을 경우 예외처리 해야 함
            Meeting meeting = meetingRepository.findById(meetingId).get();
            // TODO : 현재 10년치가 아니라 전부 다 보내는 걸로 해놨음. 나중에 바꿔야 함
            List<Schedule> schedules = scheduleRepository.findAllByMeeting(meeting);
            for (Schedule schedule : schedules) {
                scheduleDetailResponseDtos.add(ScheduleDetailResponseDto.builder()
                                .schedule(schedule)
                                .meeting(meeting)
                                .build());
            }
        }

        return ScheduleWholeResponseDto.builder()
                .startYear(startYear)
                .scheduleDetail(scheduleDetailResponseDtos)
                .build();
    }

    @Override
    public ScheduleDetailResponseDto getDetailSchedule(Long scheduleId) {
        ScheduleDetailResponseDto scheduleDetailResponseDto;
        if (scheduleRepository.findById(scheduleId).isPresent()) {
            Schedule schedule = scheduleRepository.findById(scheduleId).get();
            Meeting meeting = schedule.getMeeting();
            scheduleDetailResponseDto = ScheduleDetailResponseDto.builder()
                    .schedule(schedule)
                    .meeting(meeting)
                    .build();
            return scheduleDetailResponseDto;
        }
        return null;
    }

    @Override
    public void makeSchedule(ScheduleDetailRequestDto scheduleDetailRequestDto) {
        Schedule schedule = scheduleDetailRequestDto.toEntity();
        scheduleRepository.save(schedule);
    }

//    @Override
//    public void updateSchedule(Schedule schedule) {
//
//    }

}
