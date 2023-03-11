package com.talk.controller;

import com.talk.dto.PositionModify;
import com.talk.dto.request.PositionCreateRequest;
import com.talk.dto.response.PositionDetailResponse;
import com.talk.dto.response.PositionWholeResponse;
import com.talk.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @GetMapping("/position/home")
    public ResponseEntity<PositionWholeResponse> positionHome(@RequestBody HashMap<String, Long> map) {

        return ResponseEntity.ok(
                positionService.positionHome(map)
        );

    }

    @GetMapping("/position/detail")
    public ResponseEntity<PositionDetailResponse> positionDetail(@RequestBody HashMap<String, Long> map) {

        return ResponseEntity.ok(
                positionService.positionDetail(map)
        );

    }

    @PostMapping("/position/detail")
    public void positionCreation(@RequestBody PositionCreateRequest positionCreateRequest) {

        positionService.positionCreation(positionCreateRequest);

    }


    @PutMapping("/position/detail")
    public void positionModify(@RequestBody PositionModify positionModify) {

        positionService.positionModify(positionModify);

    }

    @DeleteMapping("/position/detail")
    public void positionDelete(@RequestBody HashMap<String, Long> map) {

        positionService.positionDelete(map);

    }

}
