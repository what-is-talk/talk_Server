package com.talk.controller;

import com.talk.dto.response.InviteCodeResponseDto;
import com.talk.dto.response.MemberDetailResponseDto;
import com.talk.dto.response.MemberListResponseDto;
import com.talk.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"member"})
@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "그룹의 멤버 리스트 반환")
    @GetMapping("/")
    public List<MemberListResponseDto> getMemberAll(@Valid @RequestParam Long groupId) {
        return memberService.getAllMemberListDto(groupId);
    }

    @ApiOperation(value = "멤버 개인 페이지")
    @GetMapping("/detail")
    public MemberDetailResponseDto getMemberDetail (@Valid @RequestParam Long userId,
                                                    @Valid @RequestParam Long groupId) {
        return memberService.getMemberDetail(userId, groupId);
    }

    @ApiOperation(value = "멤버 초대 코드")
    @GetMapping("/invite")
    public InviteCodeResponseDto getGroupInviteCode (@Valid @RequestParam Long groupId) {
        return memberService.getInviteCode(groupId);
    }

    @ApiOperation(value = "멤버 탈퇴")
    @DeleteMapping("/")
    public ResponseEntity<?> withDrawMember(@Valid @RequestParam Long userId) {
        memberService.withDrawMember(userId);
        return ResponseEntity.ok().build();
    }
}
