package com.talk.service;

import com.talk.domain.*;
import com.talk.dto.PositionModify;
import com.talk.dto.request.PositionCreateRequest;
import com.talk.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public PositionWholeResponse positionHome(HashMap<String, Long> map) {

        List<PositionResponse> positionList = new ArrayList<>();
        Meeting meeting = meetingRepository.findById(map.get("groupId")).get();
        List<Position> positions = positionRepository.findByMeeting(meeting);

        for (Position el : positions) {
            List<MemberPosition> memberPositions = memberPositionRepository.findByPositionId(el.getId());
            Integer memberCount = memberPositions.size();
            PositionResponse positionResponse = PositionResponse.builder().name(el.getName())
                    .color(el.getColor()).memberCount(memberCount).build();
            positionList.add(positionResponse);
        }

        return PositionWholeResponse.builder().positionList(positionList).build();

    }

    public PositionDetailResponse positionDetail(HashMap<String, Long> map) {

        List<MemberPosition> memberPositions = memberPositionRepository.findByPositionId(map.get("positionId"));
        List<PositionPrivilege> positionPrivileges = positionPrivilegeRepository.findByPositionId(map.get("positionId"));
        List<PositionMemberElement> memberList = new ArrayList<>();
        List<PrivilegeListElement> privilegeList = new ArrayList<>();

        Position position = positionRepository.findById(map.get("positionId")).get();

        for (MemberPosition memberPosition : memberPositions) {
            Member member = memberPosition.getMember();
            PositionMemberElement positionMemberElement = PositionMemberElement.builder().memberId(member.getId())
                    .memberName(member.getName()).profileImage(member.getProfileImage()).build();
            memberList.add(positionMemberElement);
        }

        for (PositionPrivilege positionPrivilege : positionPrivileges) {
            Privilege privilege = positionPrivilege.getPrivilege();
            PrivilegeListElement privilegeListElement = PrivilegeListElement.builder().privilegeId(privilege.getId())
                    .privilegeName(privilege.getName()).build();
            privilegeList.add(privilegeListElement);
        }

        return PositionDetailResponse.builder().name(position.getName()).color(position.getColor())
                .memberList(memberList).privilegeList(privilegeList).build();

    }

    public void positionCreation(PositionCreateRequest positionCreateRequest) {

        Meeting meeting = meetingRepository.findById(positionCreateRequest.getGroupId()).get();
        List<Long> memberIdList = positionCreateRequest.getMemberList();
        List<Long> privilegeIdList = positionCreateRequest.getPrivilegeList();

        Position position = Position.builder().name(positionCreateRequest.getName()).meeting(meeting)
                .color(positionCreateRequest.getColor()).build();

        positionRepository.save(position);

        for (Long memberId : memberIdList) {
            Member member = memberRepository.findById(memberId).get();
            MemberPosition memberPosition = MemberPosition.builder().member(member).position(position).build();
            memberPositionRepository.save(memberPosition);
        }

        for (Long privilegeId : privilegeIdList) {
            Privilege privilege = privilegeRepository.findById(privilegeId).get();
            PositionPrivilege positionPrivilege = PositionPrivilege.builder().position(position).privilege(privilege).build();
            positionPrivilegeRepository.save(positionPrivilege);
        }

    }


    public void positionModify(PositionModify positionModify) {

        Position position = positionRepository.findById(positionModify.getPositionId()).get();

        List<Long> memberList = positionModify.getMemberList();
        List<Long> privilegeList = positionModify.getPrivilegeList();

        Position modifiedPosition = Position.builder().id(positionModify.getPositionId()).name(positionModify.getName())
                .meeting(position.getMeeting()).color(positionModify.getColor()).build();

        positionRepository.save(modifiedPosition);

        for (Long memberId : memberList) {
            Member member = memberRepository.findById(memberId).get();
            if (!memberPositionRepository.existsByMemberAndPosition(member, modifiedPosition)) {
                MemberPosition memberPosition = MemberPosition.builder().member(member).position(position).build();
                memberPositionRepository.save(memberPosition);
            }

        }

        for (Long privilegeId : privilegeList) {
            Privilege privilege = privilegeRepository.findById(privilegeId).get();
            if (!positionPrivilegeRepository.existsByPositionAndPrivilege(modifiedPosition, privilege)) {
                PositionPrivilege positionPrivilege = PositionPrivilege.builder().position(position).privilege(privilege).build();
                positionPrivilegeRepository.save(positionPrivilege);
            }
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
