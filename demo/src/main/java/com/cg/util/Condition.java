package com.cg.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Condition {
    private String name;

    private Operator operator;

    private String value;
}
