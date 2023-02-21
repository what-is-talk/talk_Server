package com.talk.service;

import com.talk.domain.*;
import com.talk.domain.enumpack.Color;
import com.talk.dto.PositionModify;
import com.talk.dto.request.PositionCreateRequest;
import com.talk.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;
    private final MeetingRepository meetingRepository;
    private final MemberPositionRepository memberPositionRepository;
    private final PositionPrivilegeRepository positionPrivilegeRepository;
    private final MemberRepository memberRepository;
    private final PrivilegeRepository privilegeRepository;

    public PositionWholeResponseDto positionHome(HashMap<String, Long> map) {

        List<PositionResponseDto> positionList = new ArrayList<>();
        Meeting meeting = meetingRepository.findById(map.get("groupId")).get();
        List<Position> positions = positionRepository.findByMeeting(meeting);

        for (Position el : positions) {
            List<MemberPosition> memberPositions = memberPositionRepository.findByPositionId(el.getId());
            Integer memberCount = memberPositions.size();
            PositionResponseDto positionResponse = PositionResponseDto.builder().name(el.getName())
                    .color(el.getColor()).memberCount(memberCount).build();
            positionList.add(positionResponse);
        }

        return PositionWholeResponseDto.builder().positionList(positionList).build();

    }

    public PositionDetailResponse positionDetail(HashMap<String, Long> map) {

        List<MemberPosition> memberPositions = memberPositionRepository.findByPositionId(map.get("positionId"));
        List<PositionPrivilege> positionPrivileges = positionPrivilegeRepository.findByPositionId(map.get("positionId"));
        List<PositionMemberElementDto> memberList = new ArrayList<>();
        List<PrivilegeListElementDto> privilegeList = new ArrayList<>();

        Position position = positionRepository.findById(map.get("positionId")).get();

        for (MemberPosition memberPosition : memberPositions) {
            Member member = memberPosition.getMember();
            PositionMemberElementDto positionMemberElement = PositionMemberElementDto.builder().memberId(member.getId())
                    .memberName(member.getName()).profileImage(member.getProfileImage()).build();
            memberList.add(positionMemberElement);
        }

        for (PositionPrivilege positionPrivilege : positionPrivileges) {
            Privilege privilege = positionPrivilege.getPrivilege();
            PrivilegeListElementDto privilegeListElement = PrivilegeListElementDto.builder().privilegeId(privilege.getId())
                    .privilegeName(privilege.getName()).build();
            privilegeList.add(privilegeListElement);
        }

        return PositionDetailResponse.builder().name(position.getName()).color(position.getColor())
                .memberList(memberList).privilegeList(privilegeList).build();

    }

    public void positionCreation(PositionCreateRequest positionCreateRequest) {



        Meeting meeting = meetingRepository.findById(positionCreateRequest.getGroupId()).get();



//        List<Object> memberList = (List<Object>) map.get("memberList");
//        List<Object> privilegeList = (List<Object>) map.get("privilegeList");
//        Meeting meeting = meetingRepository.findById((Long) map.get("groupId")).get();
//
//        Position position = Position.builder().name((String) map.get("name")).meeting(meeting)
//                .color(Color.valueOf(map.get("color").toString())).build();
//
//        for (int i = 0; i < memberList.size(); i++) {
//            JSONObject memberListEl = new JSONObject(memberList.get(i).toString());
//            Long memberId = Long.valueOf(memberListEl.get("memberId").toString());
//            Member member = memberRepository.findById(memberId).get();
//            MemberPosition memberPosition = MemberPosition.builder().member(member).position(position).build();
//            memberPositionRepository.save(memberPosition);
//        }
//
//        for (int i = 0; i < privilegeList.size(); i++) {
//            JSONObject privilegeListEl = new JSONObject(privilegeList.get(i).toString());
//            Long privilegeId = Long.valueOf(privilegeListEl.get("privilegeId").toString());
//            Privilege privilege = privilegeRepository.findById(privilegeId).get();
//            PositionPrivilege positionPrivilege = PositionPrivilege.builder().position(position).privilege(privilege).build();
//            positionPrivilegeRepository.save(positionPrivilege);
//        }
//
//        positionRepository.save(position);

    }


    public void positionModify(PositionModify positionModify) { // 원래 그 역할에 있는 멤버는 예외처리

        Position position = positionRepository.findById(positionModify.getPositionId()).get();

        List<Long> memberList = positionModify.getMemberList();
        List<Long> privilegeList = positionModify.getPrivilegeList();

        Position modifiedPosition = Position.builder().id(positionModify.getPositionId()).name(positionModify.getName())
                .meeting(position.getMeeting()).color(positionModify.getColor()).build();

        positionRepository.save(modifiedPosition);

        for (Long memberId : memberList) {
            Member member = memberRepository.findById(memberId).get();
            MemberPosition memberPosition = MemberPosition.builder().member(member).position(position).build();
            memberPositionRepository.save(memberPosition);
        }

        for (Long privilegeId : privilegeList) {
            Privilege privilege = privilegeRepository.findById(privilegeId).get();
            PositionPrivilege positionPrivilege = PositionPrivilege.builder().position(position).privilege(privilege).build();
            positionPrivilegeRepository.save(positionPrivilege);
        }

    }

    public void positionDelete(HashMap<String, Long> map) {

        List<MemberPosition> memberPositions = memberPositionRepository.findByPositionId(map.get("positionId"));
        for (MemberPosition memberPosition : memberPositions) {
            memberPositionRepository.delete(memberPosition);
        }

        List<PositionPrivilege> positionPrivileges = positionPrivilegeRepository.findByPositionId(map.get("positionId"));
        for (PositionPrivilege positionPrivilege : positionPrivileges) {
            positionPrivilegeRepository.delete(positionPrivilege);
        }

        Position position = positionRepository.findById(map.get("positionId")).get();
        positionRepository.delete(position);

    }


}
