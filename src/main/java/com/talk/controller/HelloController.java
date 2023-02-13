package com.talk.controller;

import com.talk.dto.response.HomeResponse;
import com.talk.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Api(tags = {"home"})
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HomeService homeService;

    @ApiOperation(value = "home리턴")
    @GetMapping("/home")
    public HomeResponse home(@RequestBody HashMap<String, Object> homeRequest) {
        return homeService.home(homeRequest);
    }

    @GetMapping("/home/select")
    public HashMap<String, Object> select(@RequestBody HashMap<String, Long> groupId) {
        return homeService.select(groupId);
    }
}
