package me.choi.inflearnthejavatest.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class Study {

    @Id
    private Long id;

    private String name;

    private int number;

    @OneToOne
    private Member owner;

}
