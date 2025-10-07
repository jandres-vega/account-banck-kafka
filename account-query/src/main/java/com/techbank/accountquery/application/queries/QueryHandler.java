package com.techbank.accountquery.application.queries;

import com.techbank.cqrscore.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllAccountQuery findAllAccountQuery);
    List<BaseEntity> handle(FindAccountBalanceQuery findAccountBalanceQuery);
    List<BaseEntity> handle(FindAccountByIdQuery findAccountByIdQuery);
    List<BaseEntity> handle(FindAccountByHolderQuery findAccountByHolderQuery);
}
