package com.talk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.talk.domain.Meeting;
import com.talk.domain.MeetingRepository;
import com.talk.domain.MemberMeeting;
import com.talk.domain.MemberMeetingRepository;
import com.talk.dto.response.HomeResponse;
import lombok.RequiredArgsConstructor;
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


    public HomeResponse home(HashMap<String, Object> homeRequest) throws JsonProcessingException {

        Integer memberCount;
        List<String> groupList = new ArrayList<>();

        List<MemberMeeting> memberMeetings = memberMeetingRepository.findByMemberId(Long.valueOf(String.valueOf(homeRequest.get("userId"))));

        for (MemberMeeting el : memberMeetings) {
            memberCount = memberMeetings.size();
            Map<String, Object> map = new HashMap<>();
            map.put("id", el.getMeeting().getId());
            map.put("name", el.getMeeting().getName());
            map.put("image_url", el.getMeeting().getGroupImageUrl());
            map.put("member_count", memberCount);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(map);

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
