package com.talk.dto.response;

import com.talk.domain.enumpack.Color;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PositionResponse {

    private String name;
    private Color color;
    private Integer memberCount;

}
