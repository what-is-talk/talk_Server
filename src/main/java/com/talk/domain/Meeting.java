package com.talk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Meeting extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_id")
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    private String groupImageUrl;

    @Column
    @NotNull
    private String invite_code;

    @JsonBackReference
    @OneToMany(mappedBy = "meeting")
    private List<MemberMeeting> member_meetings;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "leader_id")
    private Member leader;

    @JsonBackReference
    @OneToMany(mappedBy = "meeting")
    private List<Position> positions;

    @JsonBackReference
    @OneToMany(mappedBy = "meeting")
    private List<Chatroom> chatrooms;

    @JsonBackReference
    @OneToMany(mappedBy = "meeting")
    private List<Schedule> schedules;
}
