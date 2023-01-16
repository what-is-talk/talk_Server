package com.talk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "moim")
public class Group {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @JsonBackReference
    @OneToMany(mappedBy = "group")
    private List<MemberGroup> member_groups;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "leader_id")
    private Member leader;
}
