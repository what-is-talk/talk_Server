package com.talk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.talk.domain.enumpack.Color;
import com.talk.domain.enumpack.AuthProvider;
import com.talk.domain.enumpack.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity{

    @Id
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private Color personalColor;

    private String profileImage;

    private Role role;

    private AuthProvider authProvider;

    @JsonBackReference
    @OneToMany(mappedBy = "member")
    private List<MemberMeeting> memberMeetings;

    @JsonBackReference
    @OneToMany(mappedBy = "leader")
    private List<Meeting> meetings;

    @JsonBackReference
    @OneToMany(mappedBy = "member")
    private List<MemberChatroom> memberChatrooms;

    @JsonBackReference
    @OneToMany(mappedBy = "member")
    private List<MemberPosition> memberPositions;

    @Builder
    public Member(Long id, String email, String name, String profileImage, Color personalColor, Role role, AuthProvider authProvider) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileImage = profileImage;
        this.personalColor = personalColor;
        this.role = role;
        this.authProvider = authProvider;
    }
}