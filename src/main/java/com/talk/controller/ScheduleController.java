package com.talk.controller;

import com.talk.dto.request.ScheduleCreateRequest;
import com.talk.dto.request.ScheduleUpdateRequest;
import com.talk.dto.response.ScheduleDetailResponse;
import com.talk.dto.response.ScheduleWholeResponse;
import com.talk.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"schedule"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiOperation("캘린더 메인 페이지")
    @GetMapping
    public ScheduleWholeResponse calenderMain(@Valid @RequestParam Long groupId, @Valid @RequestParam Long year) {
        return scheduleService.getWholeSchedule(groupId, year);
    }

    @ApiOperation("캘린더 상세 페이지")
    @GetMapping("/detail")
    public ScheduleDetailResponse calenderDetail(@Valid @RequestParam Long scheduleId) {
        return scheduleService.getDetailSchedule(scheduleId);
    }

    @ApiOperation("스케쥴 생성")
    @PostMapping("/detail")
    public ResponseEntity<?> makeSchedule (@Valid @RequestBody ScheduleCreateRequest scheduleCreateRequest) {
        scheduleService.makeSchedule(scheduleCreateRequest);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("스케쥴 수정")
    @PutMapping("/detail")
    public ResponseEntity<?> updateSchedule (@Valid @RequestBody ScheduleUpdateRequest scheduleUpdateRequest) {
        scheduleService.updateSchedule(scheduleUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("스케쥴 삭제")
    @DeleteMapping("/detail")
    public ResponseEntity<?> deleteSchedule (@Valid @RequestParam Long scheduleId) {
        String ans = scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok(ans);
    }
}
