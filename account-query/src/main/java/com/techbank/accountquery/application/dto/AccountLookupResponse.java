package com.techbank.accountquery.application.dto;

import com.techbank.accountcommon.dto.BaseResponse;
import com.techbank.accountquery.domain.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountLookupResponse extends BaseResponse {

    private List<BankAccount> accounts;
    public AccountLookupResponse(String message) {
        super(message);
    }
}
