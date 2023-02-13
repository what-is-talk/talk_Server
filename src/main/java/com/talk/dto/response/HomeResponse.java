package com.talk.dto.response;

import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.List;

@Getter
@Builder
public class HomeResponse {

    List<String> groupList;

}
