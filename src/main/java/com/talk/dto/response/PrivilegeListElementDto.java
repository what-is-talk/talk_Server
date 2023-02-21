package com.talk.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PrivilegeListElementDto {

    private Long privilegeId;
    private String privilegeName;

}
