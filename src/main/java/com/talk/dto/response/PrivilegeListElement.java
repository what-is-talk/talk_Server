package com.talk.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PrivilegeListElement {

    private Long privilegeId;
    private String privilegeName;

}
