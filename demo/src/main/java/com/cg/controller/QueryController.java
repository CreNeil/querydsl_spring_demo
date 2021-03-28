package com.cg.controller;

import com.cg.controller.dto.QueryCondition;
import com.cg.domain.QComputer;
import com.cg.util.JPAQueryBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/query")
public class QueryController {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PostMapping("/post")
    public List<Object> query(@RequestBody QueryCondition queryCondition) throws NoSuchFieldException {

        JPAQuery query = JPAQueryBuilder.buildJPAQueryBase(jpaQueryFactory, QComputer.computer,
                queryCondition.getAndCondition(), queryCondition.getOrCondition());
        return query.fetch();
    }
}
