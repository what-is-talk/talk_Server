package com.talk.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PositionMemberElement {

    private Long memberId;
    private String memberName;
    private String profileImage;

}
