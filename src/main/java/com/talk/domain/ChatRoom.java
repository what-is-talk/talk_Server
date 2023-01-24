package com.talk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@DynamicUpdate
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    private String id;

//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "lastChatMesgId")
//    private ChatMessage lastChatMesg;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "ChatRoom_Members",
//            joinColumns = @JoinColumn(name = "chatRoomId"),
//            inverseJoinColumns = @JoinColumn(name = "memberId"))
//    private Set<Member> chatRoomMembers = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "chatroom")
    private List<ChatRoomMembers> chatroom_members;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public static ChatRoom create() {

        ChatRoom room = new ChatRoom();
        room.setId(UUID.randomUUID().toString());

        return room;
    }

//    public void addMembers(Member roomMaker, Member guest) {
//        this.chatRoomMembers.add(roomMaker);
//        this.chatRoomMembers.add(guest);
//    }
}

