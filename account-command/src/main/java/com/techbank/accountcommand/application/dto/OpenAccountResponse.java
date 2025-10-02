package com.techbank.accountcommand.application.dto;

import com.techbank.accountcommon.dto.BaseResponse;
import lombok.Data;

@Data
public class OpenAccountResponse extends BaseResponse {
    private String id;

    public OpenAccountResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
