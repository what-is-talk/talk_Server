package com.talk.service;

import com.talk.domain.Meeting;
import com.talk.domain.MeetingRepository;
import com.talk.domain.Schedule;
import com.talk.domain.ScheduleRepository;
import com.talk.dto.request.ScheduleCreateRequest;
import com.talk.dto.request.ScheduleUpdateRequest;
import com.talk.dto.response.ScheduleDetailResponse;
import com.talk.dto.response.ScheduleWholeElementResponse;
import com.talk.dto.response.ScheduleWholeResponse;
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
    public ScheduleWholeResponse getWholeSchedule(Long meetingId, Long year) {
        Long startYear = (year / 10) * 10;
        List<ScheduleWholeElementResponse> scheduleWholeElementResponses = new ArrayList<>();
        if (meetingRepository.findById(meetingId).isPresent()){ // TODO : 그룹이 없을 경우 예외처리 해야 함
            Meeting meeting = meetingRepository.findById(meetingId).get();
            // TODO : 현재 10년치가 아니라 전부 다 보내는 걸로 해놨음. 나중에 바꿔야 함
            List<Schedule> schedules = scheduleRepository.findAllByMeeting(meeting);
            for (Schedule schedule : schedules) {
                scheduleWholeElementResponses.add(ScheduleWholeElementResponse.builder()
                                .schedule(schedule)
                                .build());
            }
            return ScheduleWholeResponse.builder()
                    .startYear(startYear)
                    .groupName(meeting.getName())
                    .scheduleWholeElementResponses(scheduleWholeElementResponses)
                    .build();
        }
        return null;


    }

    @Override
    public ScheduleDetailResponse getDetailSchedule(Long scheduleId) {
        ScheduleDetailResponse scheduleDetailResponse;
        if (scheduleRepository.findById(scheduleId).isPresent()) {
            Schedule schedule = scheduleRepository.findById(scheduleId).get();
            scheduleDetailResponse = ScheduleDetailResponse.builder()
                    .schedule(schedule)
                    .build();
            return scheduleDetailResponse;
        }
        return null;
    }

    @Override
    public Long makeSchedule(ScheduleCreateRequest scheduleCreateRequest) {
        Schedule schedule = scheduleCreateRequest.toEntity(meetingRepository);
        scheduleRepository.save(schedule);
        return schedule.getId();
    }

    @Override
    @Transactional
    public void updateSchedule(ScheduleUpdateRequest scheduleUpdateRequest) {
        Schedule schedule = scheduleUpdateRequest.toEntity();
        scheduleRepository.save(schedule);
    }

    @Override
    @Transactional
    public String deleteSchedule(Long scheduleId) {
        if (scheduleRepository.findById(scheduleId).isPresent()) {
            Schedule schedule = scheduleRepository.findById(scheduleId).get();
            scheduleRepository.delete(schedule);
            return "ok";
        }
        return "fail";
        // TODO : 예외처리 해야 함
    }

}
