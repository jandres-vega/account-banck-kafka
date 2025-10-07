package com.techbank.cqrscore.infrastructure;

import com.techbank.cqrscore.domain.BaseEntity;
import com.techbank.cqrscore.queries.BaseQuery;
import com.techbank.cqrscore.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
