package com.talk.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(MemberMeetingID.class)
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MemberMeeting extends BaseTimeEntity implements Serializable {
    @Id
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    Member member;

    @Id
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id")
    Meeting meeting;

}
