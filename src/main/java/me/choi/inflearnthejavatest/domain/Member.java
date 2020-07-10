package me.choi.inflearnthejavatest.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class Member {

    @Id
    private Long id;

    private String name;

}
