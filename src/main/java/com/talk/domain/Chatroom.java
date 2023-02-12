package com.talk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@DynamicUpdate
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Chatroom extends BaseTimeEntity{

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "chatroom_id")
    private String id;

    @JsonBackReference
    @OneToMany(mappedBy = "chatroom")
    private List<MemberChatroom> memberChatrooms;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id")
    Meeting meeting;

    public static Chatroom create() {

        Chatroom room = new Chatroom();
        room.setId(UUID.randomUUID().toString());

        return room;
    }

//    public void addMembers(Member roomMaker, Member guest) {
//        this.chatRoomMembers.add(roomMaker);
//        this.chatRoomMembers.add(guest);
//    }
}

