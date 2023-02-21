package com.talk.dto.request;

import com.talk.domain.enumpack.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PositionCreateRequest {

    private Long groupId;
    private String name;
    private Color color;
    private List<Long> memberList;
    private List<Long> privilegeList;

}
