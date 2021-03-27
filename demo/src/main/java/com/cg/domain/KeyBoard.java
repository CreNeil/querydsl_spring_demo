package com.cg.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class KeyBoard {

    private String series;

    private String makers;

    private String owner;
}
