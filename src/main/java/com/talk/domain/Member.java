package com.talk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    @JsonBackReference
    @OneToMany(mappedBy = "member")
    private List<MemberGroup> member_groups;

    @JsonBackReference
    @OneToOne(mappedBy = "leader")
    private Group groupOfLeader;
}