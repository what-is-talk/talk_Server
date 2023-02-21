package com.talk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table
public class Position extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "position_id")
    private Long id;

    @Column(name = "name")
    private String name;

    // TODO : ManyToOne 이 빠졌음 좀 더 알아봐야할 듯
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @JsonBackReference
    @OneToMany(mappedBy = "position")
    private List<MemberPosition> memberPositions;

    @JsonBackReference
    @OneToMany(mappedBy = "position")
    private List<PositionPrivilege> positionPrivileges;

    private Color color;
}
