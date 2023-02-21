package com.talk.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPosition extends BaseTimeEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_position_id")
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;


    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "Position_id")
    Position position;


}
