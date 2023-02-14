package com.talk.controller;

import com.talk.dto.request.ScheduleDetailRequestDto;
import com.talk.dto.response.ScheduleDetailResponseDto;
import com.talk.dto.response.ScheduleWholeResponseDto;
import com.talk.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiOperation("캘린더 메인 페이지")
    @GetMapping
    public ScheduleWholeResponseDto calenderMain(@Valid @RequestParam Long groupId, @Valid @RequestParam Long year) {
        return scheduleService.getWholeSchedule(groupId, year);
    }

    @ApiOperation("캘린더 상세 페이지")
    @GetMapping("/detail")
    public ScheduleDetailResponseDto calenderDetail(@Valid @RequestParam Long scheduleId) {
        return scheduleService.getDetailSchedule(scheduleId);
    }

    @ApiOperation("스케쥴 생성")
    @PostMapping("/detail")
    public ResponseEntity<?> makeSchedule (@Valid @RequestParam ScheduleDetailRequestDto scheduleDetailRequestDto) {
        scheduleService.makeSchedule(scheduleDetailRequestDto);
        return ResponseEntity.ok().build();
    }

//    @ApiOperation("스케쥴 수정")
//    @PostMapping("/detail")
//    public ResponseEntity<?> updateSchedule (@Valid @RequestParam Schedule schedule) {
//        scheduleService.updateSchedule(schedule);
//        return ResponseEntity.ok().build();
//    }
}
