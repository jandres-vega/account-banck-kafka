package com.techbank.accountcommand.infrastructure.rest.controllers;

import com.techbank.accountcommand.application.commands.CloseAccountCommand;
import com.techbank.accountcommon.dto.BaseResponse;
import com.techbank.cqrscore.exceptions.AggregateNotFoundException;
import com.techbank.cqrscore.infrastructure.CommandDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/closeaccount")
public class CloseAccountController {

    private final Logger logger = Logger.getLogger(CloseAccountController.class.getName());

    private final CommandDispatcher commandDispatcher;

    public CloseAccountController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable(value = "id") String id) {
        try {

            commandDispatcher.send(new CloseAccountCommand(id));
            return ResponseEntity.ok(new BaseResponse("Close account request completed successfully"));
        } catch (IllegalStateException | AggregateNotFoundException e) {
            logger.warning("Client made a bad request - " + e.getMessage());
            return ResponseEntity.badRequest().body(new BaseResponse(e.getMessage()));
        }
    }
}
