package com.techbank.cqrscore.queries;

import com.techbank.cqrscore.domain.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {

    List<BaseEntity> handle(T query);
}
