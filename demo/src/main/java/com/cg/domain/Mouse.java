package com.cg.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Mouse {

    @Id
    private String series;

    private String makers;

    private String owner;
}
