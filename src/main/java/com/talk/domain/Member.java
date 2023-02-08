package com.talk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.talk.domain.enumpack.Color;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private Color personalColor;

    private String profileImage;

    @JsonBackReference
    @OneToMany(mappedBy = "member")
    private List<MemberMeeting> memberMeetings;

    @JsonBackReference
    @OneToOne(mappedBy = "leader")
    private Meeting meetingOfLeader;

    @JsonBackReference
    @OneToMany(mappedBy = "member")
    private List<MemberChatroom> memberChatrooms;
}