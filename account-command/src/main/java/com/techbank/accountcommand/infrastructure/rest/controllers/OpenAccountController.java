package com.techbank.accountcommand.infrastructure.rest.controllers;

import com.techbank.accountcommand.application.commands.OpenAccountCommand;
import com.techbank.accountcommand.application.dto.OpenAccountResponse;
import com.techbank.accountcommon.dto.BaseResponse;
import com.techbank.cqrscore.infrastructure.CommandDispatcher;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/openbankaccount")
public class OpenAccountController {

    private final Logger logger = Logger.getLogger(OpenAccountController.class.getName());

    private final CommandDispatcher commandDispatcher;

    public OpenAccountController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command) {

        var id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(new OpenAccountResponse("Bank account created successfully", id), HttpStatus.CREATED);
        }catch (IllegalStateException e) {
            logger.warning("Client made a bad request - " + e.getMessage());
            return ResponseEntity.badRequest().body(new BaseResponse(e.getMessage()));
        }catch (Exception e) {
            logger.warning("Client made a bad request - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse("An error occurred while processing the request"));
        }
    }
}
