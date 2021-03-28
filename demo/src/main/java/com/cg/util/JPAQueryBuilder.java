package com.cg.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.io.IOException;
import java.util.Set;

public class JPAQueryBuilder {

    private static final MapperFacade mapper = new DefaultMapperFactory.Builder().build().getMapperFacade();

    public static <T extends EntityPathBase> JPAQuery buildJPAQueryBase(
            JPAQueryFactory queryFactory, T t, Set<Condition> andConditions, Set<Condition> orConditions)
            throws NoSuchFieldException, IOException {
        JPAQuery q = queryFactory.selectFrom(t);
        Class typeOfRootClazz = t.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        for (Condition condition : andConditions) {
            booleanBuilder.and(Expressions.predicate(condition.getOperator().getOps(),
                    Expressions.path(String.class, t, condition.getName()),
                    ConstantImpl.create(mapper.map(condition.getValue(),
                            getActualClass(typeOfRootClazz, condition.getName())))));
        }

        for (Condition condition : orConditions) {
            booleanBuilder.or(Expressions.predicate(condition.getOperator().getOps(),
                    Expressions.path(String.class, t, condition.getName()),
                    ConstantImpl.create(mapper.map(condition.getValue(),
                            getActualClass(typeOfRootClazz, condition.getName())))));
        }
        q.where(booleanBuilder);
        return q;
    }

    private static Class getActualClass(Class root, String field) throws NoSuchFieldException {
        String[] fields = field.split("\\.");
        if (fields.length == 1) return root.getDeclaredField(field).getType();
        for (int i = 0; i < fields.length - 1; i++) {
            root = root.getDeclaredField(fields[i]).getType();
        }
        return root.getDeclaredField(fields[fields.length - 1]).getType();
    }
}
