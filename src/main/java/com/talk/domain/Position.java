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
@Table (uniqueConstraints = {
            @UniqueConstraint(
                    name = "roll_unique",
                    columnNames = {"meeting", "name"})})
public class Position extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long id;

    @Column(unique = true, name = "name")
    private String name;

    @Column(unique = true)
    @JsonManagedReference
    @JoinColumn(name = "meeting_id")
    private Long meeting;

    @JsonBackReference
    @OneToMany(mappedBy = "position")
    private List<MemberPosition> memberPositions;

    @JsonBackReference
    @OneToMany(mappedBy = "position")
    private List<PositionPrivilege> positionPrivileges;
}
