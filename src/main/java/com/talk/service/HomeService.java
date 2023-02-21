package com.talk.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
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
        List<Object> groupList = new ArrayList<>();

        List<MemberMeeting> memberMeetings = memberMeetingRepository.findByMemberId(Long.valueOf(String.valueOf(homeRequest.get("userId"))));

        for (MemberMeeting el : memberMeetings) {
            List<MemberMeeting> memberMeetingList = memberMeetingRepository.findByMeetingId(el.getMeeting().getId());
            memberCount = memberMeetingList.size();
            Map<String, Object> map = new HashMap<>();
            map.put("id", el.getMeeting().getId());
            map.put("name", el.getMeeting().getName());
            map.put("profileImage", el.getMeeting().getGroupImageUrl());
            map.put("memberCount", memberCount);

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

        map.put("groupId", groupId.get("groupId"));
        map.put("groupName", meeting.getName());
        map.put("memberCount", memberCount);

        return map;

    }

}
