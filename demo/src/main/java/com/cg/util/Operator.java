package com.cg.util;

import com.querydsl.core.types.Ops;

public enum Operator {
    EQ(Ops.EQ),
    NE(Ops.NE),
    GT(Ops.GT),
    LT(Ops.LT),
    LIKE(Ops.LIKE),
    START_WITH(Ops.STARTS_WITH),
    END_WITH(Ops.ENDS_WITH),
    ;

    private Ops ops;

    Operator(Ops ops) {
        this.ops = ops;
    }

    public Ops getOps() {
        return this.ops;
    }

    public void setOps(Ops ops) {
        this.ops = ops;
    }
}
