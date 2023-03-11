package com.talk.controller;

import com.talk.dto.response.MemberDetailResponse;
import com.talk.dto.response.MemberListResponse;
import com.talk.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"member"})
@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "모든 멤버 리스트 반환 테스트용")
    @GetMapping("/all")
    public MemberListResponse getMemberAll() {
        return memberService.getAllMemberList();
    }

    @ApiOperation(value = "그룹의 멤버 리스트 반환")
    @GetMapping
    public MemberListResponse getMemberAll(@Valid @RequestParam Long groupId) {
        return memberService.getAllMemberListofMeeting(groupId);
    }

    @ApiOperation(value = "멤버 개인 페이지")
    @GetMapping("/detail")
    public MemberDetailResponse getMemberDetail (@Valid @RequestParam Long userId,
                                                 @Valid @RequestParam Long groupId) {
        return memberService.getMemberDetail(userId, groupId);
    }

    @ApiOperation(value = "멤버 탈퇴")
    @DeleteMapping
    public ResponseEntity<?> withDrawMember(@Valid @RequestParam Long userId) {
        memberService.withDrawMember(userId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "멤버 가입 테스트용 사용 금지")
    @PostMapping
    public ResponseEntity<?> joinMember(@Valid @RequestParam String name,
                                        @Valid @RequestParam String email) {
        memberService.joinMemberTest(name, email);
        return ResponseEntity.ok().build();
    }

}
