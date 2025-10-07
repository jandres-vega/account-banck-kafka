package com.techbank.accountquery;

import com.techbank.accountquery.application.queries.*;
import com.techbank.cqrscore.infrastructure.QueryDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountQueryApplication {

    private final QueryDispatcher queryDispatcher;

    private final QueryHandler queryHandler;

    public AccountQueryApplication(QueryDispatcher queryDispatcher, QueryHandler queryHandler) {
        this.queryDispatcher = queryDispatcher;
        this.queryHandler = queryHandler;
    }

    public static void main(String[] args) {
		SpringApplication.run(AccountQueryApplication.class, args);
	}

    @PostConstruct
    public void registerHandler(){
        queryDispatcher.registerHandler(FindAllAccountQuery.class, queryHandler::handle);
        queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
        queryDispatcher.registerHandler(FindAccountBalanceQuery.class, queryHandler::handle);
        queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handle);
    }

}
