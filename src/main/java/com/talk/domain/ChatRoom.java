package com.talk.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;
}
