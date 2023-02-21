package com.talk.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PositionWholeResponseDto {

    private List<PositionResponseDto> positionList;

}
