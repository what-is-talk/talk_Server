package com.talk.dto;

import com.talk.domain.enumpack.Color;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public class PositionModify {

    private Long positionId;
    private String name;
    private Color color;
    private List<Long> memberList;
    private List<Long> privilegeList;

}
