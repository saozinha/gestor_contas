package com.lourenco.gestor_contas.module.account.controller;


import com.lourenco.gestor_contas.inputOutPut.TransferInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountOutput;
import com.lourenco.gestor_contas.inputOutPut.statement.StatementOutput;
import com.lourenco.gestor_contas.module.account.mapper.AccountMapper;
import com.lourenco.gestor_contas.module.account.service.AccountService;
import com.lourenco.gestor_contas.module.statement.mapper.StatementMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
@Api(value = "Account API", consumes = "application/json charset=utf-8")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Utilize para registrar uma conta", consumes = "application/json")
    public ResponseEntity<AccountOutput> create(
            @ApiParam(value = "Objeto necessario para registrar uma conta", required = true)
            @Valid @RequestBody final AccountInput accountInput) throws Exception {
        return ResponseEntity.ok(AccountMapper.toAccountOutput(this.accountService.create(accountInput)));
    }

    @GetMapping(path = "/{cpf}")
    @ApiOperation(value = "Utilize para buscar uma conta", consumes = "application/json")
    public ResponseEntity<AccountOutput> get(@ApiParam(value = "CPF da pessoa", required = true)
                            @PathVariable("cpf") String cpf ) {
        return ResponseEntity.ok(AccountMapper.toAccountOutput(this.accountService.findAccountByCpf(cpf)));
    }

    @PostMapping(path = "deposit/")
    public ResponseEntity<StatementOutput> deposit(
            @ApiParam(value = "Objeto necessario para depositar valor em uma conta", required = true)
            @Valid @RequestBody final TransferInput transferInput) throws Exception {
        return ResponseEntity.ok(StatementMapper.toStatementOutput(this.accountService.deposit(transferInput)));
    }

    @GetMapping(path = "/statement/{cpf}")
    @ApiOperation(value = "Utilize para visualizaro extrato de uma conta", consumes = "application/json")
    public Flux<StatementOutput> getStatementByCpf(@ApiParam(value = "CPF da pessoa", required = true)
                                             @PathVariable("cpf") String cpf ) {
        return Flux.fromStream(this.accountService.findStatementByCpf(cpf).stream())
                .subscribeOn(Schedulers.boundedElastic())
                .map(StatementMapper::toStatementOutput);
    }

    @PostMapping(path = "/transfer")
    @ApiOperation(value = "Utilize para tranferir valor de uma conta para outra", consumes = "application/json")
    public ResponseEntity<StatementOutput> transferToAnotherAccount(@ApiParam(value = "Objeto para transferência de valor", required = true)
                                                            @Valid @RequestBody final TransferInput transferInput) {
        return ResponseEntity.ok(StatementMapper.toStatementOutput(this.accountService.transferToAnotherAccount(transferInput)));
    }
}

