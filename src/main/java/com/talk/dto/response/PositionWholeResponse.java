package com.talk.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PositionWholeResponse {

    private List<PositionResponse> positionList;

}
