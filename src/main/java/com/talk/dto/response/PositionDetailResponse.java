package com.talk.dto.response;

import com.talk.domain.enumpack.Color;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PositionDetailResponse {

    private String name;
    private Color color;
    private List<PositionMemberElementDto> memberList;
    private List<PrivilegeListElementDto> privilegeList;

}
