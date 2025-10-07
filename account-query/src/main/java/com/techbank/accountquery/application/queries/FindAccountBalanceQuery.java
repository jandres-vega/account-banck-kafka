package com.techbank.accountquery.application.queries;

import com.techbank.accountquery.application.dto.EquityType;
import com.techbank.cqrscore.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountBalanceQuery extends BaseQuery {
    private EquityType equityType;
    private double balance;
}
