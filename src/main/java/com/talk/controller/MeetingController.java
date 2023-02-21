package com.talk.controller;

import com.talk.domain.Meeting;
import com.talk.dto.request.MeetingCreateRequest;
import com.talk.dto.response.InviteCodeResponseDto;
import com.talk.service.MeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"Meeting"})
@RequestMapping("/meeting")
@RestController
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @ApiOperation(value = "모임 리스트 출력 테스트")
    @GetMapping
    public List<Meeting> getMeetingAll() {
        return meetingService.getMeetingAll();
    }

    @ApiOperation(value = "모임 설명 리스트 테스트")
    @GetMapping("/info")
    public List<String> getMeetingInfoAll() {
        return meetingService.getMeetingAllInfo();
    }

    @ApiOperation(value = "모임 생성")
    @PostMapping
    public ResponseEntity<?> createMeeting(@Valid @RequestBody MeetingCreateRequest meetingCreateRequest) {
        meetingService.createMeeting(meetingCreateRequest);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "모임에 가입")
    @PostMapping("/member")
    public ResponseEntity<?> joinMemberMeeting(@Valid @RequestParam Long groupId,
                                        @Valid @RequestParam Long memberId) {
        meetingService.joinMemberMeeting(groupId, memberId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "모임에서 탈퇴")
    @DeleteMapping("member")
    public ResponseEntity<?> deleteMeeting(@Valid @RequestParam Long groupId,
                                         @Valid @RequestParam Long memberId) {
        meetingService.deleteMemberMeeting(groupId, memberId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "모임 삭제")
    @DeleteMapping
    public ResponseEntity<?> deleteMeeting(@Valid @RequestParam Long groupId) {
        meetingService.deleteMeeting(groupId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "멤버 초대 코드")
    @GetMapping("/invite")
    public InviteCodeResponseDto getGroupInviteCode (@Valid @RequestParam Long groupId) {
        return meetingService.getInviteCode(groupId);
    }
}
