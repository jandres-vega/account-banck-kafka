package com.techbank.accountquery.application.queries;

import com.techbank.accountquery.application.dto.EquityType;
import com.techbank.accountquery.domain.AccountRepository;
import com.techbank.accountquery.domain.BankAccount;
import com.techbank.cqrscore.domain.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountQueryHandler implements QueryHandler{

    private final AccountRepository accountQueryRepository;

    public AccountQueryHandler(AccountRepository accountQueryRepository) {
        this.accountQueryRepository = accountQueryRepository;
    }

    @Override
    public List<BaseEntity> handle(FindAllAccountQuery findAllAccountQuery) {
        Iterable<BankAccount> allAccounts = accountQueryRepository.findAll();
        List<BaseEntity> allAccountsList = new ArrayList<>();
        allAccounts.forEach(allAccountsList::add);
        return allAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountBalanceQuery query) {
        List<BankAccount> bankAccounts = query.getEquityType() == EquityType.GREATER_THAN
                ? accountQueryRepository.findByBalanceGreaterThan(query.getBalance())
                : accountQueryRepository.findByBalanceLessThan(query.getBalance());

        return bankAccounts.stream()
                .map(account -> (BaseEntity) account)
                .collect(Collectors.toList());
    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQuery findAccountByIdQuery) {
        var bankAccount = accountQueryRepository.findById(findAccountByIdQuery.getId());
        if (bankAccount.isEmpty()) return null;
        List<BaseEntity> allAccountsList = new ArrayList<>();
        allAccountsList.add(bankAccount.get());
        return allAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery findAccountByHolderQuery) {
        var bankAccount = accountQueryRepository.findByAccountHolder(findAccountByHolderQuery.getAccountHolder());
        if (bankAccount.isEmpty()) return null;
        List<BaseEntity> allAccountsList = new ArrayList<>();
        allAccountsList.add(bankAccount.get());
        return allAccountsList;
    }
}
