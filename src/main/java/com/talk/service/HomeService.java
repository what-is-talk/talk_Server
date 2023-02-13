package com.talk.service;

import com.talk.domain.Meeting;
import com.talk.domain.MeetingRepository;
import com.talk.domain.MemberMeeting;
import com.talk.domain.MemberMeetingRepository;
import com.talk.dto.response.HomeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MemberMeetingRepository memberMeetingRepository;
    private final MeetingRepository meetingRepository;


    public HomeResponse home(HashMap<String, Object> homeRequest) {

        Integer memberCount;
        List<JSONObject> groupList = new ArrayList<>();

        List<Meeting> meetings = memberMeetingRepository.findByMemberId(Long.valueOf(String.valueOf(homeRequest.get("userId"))));

        for (Meeting el : meetings) {
            memberCount = memberMeetingRepository.findByMeetingId(el.getId()).size();
            Map<String, Object> map = new HashMap<>();
            map.put("id", el.getId());
            map.put("name", el.getName());
            map.put("image_url", el.getGroupImageUrl());
            map.put("member_count", memberCount);

            JSONObject json =  new JSONObject(map);

            groupList.add(json);
        }

        return HomeResponse.builder().groupList(groupList).build();
    }

    public HashMap<String, Object> select(HashMap<String, Long> groupId) {

        Meeting meeting = meetingRepository.findById(groupId.get("groupId")).get();

        Integer memberCount;

        List<MemberMeeting> memberMeetings = memberMeetingRepository.findByMeetingId(groupId.get("groupId"));

        memberCount = memberMeetings.size();

        HashMap<String, Object> map = new HashMap<>();

        map.put("group_id", groupId);
        map.put("group_name", meeting.getName());
        map.put("member_count", memberCount);

        return map;

    }

}
