package com.cg.controller.dto;

import com.cg.util.Condition;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class QueryCondition {
    Set<Condition> andCondition;
    Set<Condition> orCondition;
}
