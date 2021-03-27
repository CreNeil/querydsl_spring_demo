package com.cg.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Monitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monitorId;

    @ManyToOne
    private Computer computer;

    private String series;
}
