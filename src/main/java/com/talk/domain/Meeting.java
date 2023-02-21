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
    private String inviteCode;

    @JsonBackReference
    @OneToMany(mappedBy = "meeting")
    private List<MemberMeeting> memberMeetings;

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

    @Override
    public String toString() {
        String ret =  "Meeting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupImageUrl='" + groupImageUrl + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", memberMeetings(가입된 멤버 id)= [";
                for (MemberMeeting memberMeeting : memberMeetings) {
                    ret += memberMeeting.getMember().getId() + ", ";
                }
                ret += "]";
                ret += ", leader id=" + leader.getId() +
                ", positions(역할)= [";
                for (Position position : positions) {
                    ret += "id: " + position.getId() + " name: " + position.getName() + ", ";
                }
                ret += "]";
                ret += ", chatrooms(채팅방 id)= [";
                for (Chatroom chatroom : chatrooms) {
                    ret += chatroom.getId() + ", ";
                }
                ret += "]";
                ret += ", schedules(일정 id)= [";
                for (Schedule schedule : schedules) {
                    ret += schedule.getId() + ", ";
                }
                ret += "]" +
                '}';
                return ret;
    }
}
