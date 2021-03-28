package com.cg.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long computerId;

    @Embedded
    private KeyBoard keyBoard;

    @OneToOne
    private Mouse mouse;

    @OneToOne
    private Monitor monitor;
}
