package com.lourenco.gestor_contas.module.account;


import com.lourenco.gestor_contas.inputOutPut.account.AccountOutput;
import com.lourenco.gestor_contas.inputOutPut.person.PersonOutput;
import com.lourenco.gestor_contas.module.person.mapper.PersonMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@Api(value = "Account API", consumes = "application/json charset=utf-8")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AccountOutput> create(
            @ApiParam(value = "Objeto necessario para registrar uma conta", required = true)
            @Valid @RequestBody final AccountOutput accountOutput) throws Exception {
        return Mono.justOrEmpty(this.accountService.create(accountOutput));
    }

    @GetMapping("/get/{cpf}")
    @ApiOperation(value = "Utilize para buscar uma conta", consumes = "application/json")
    public Mono<AccountOutput> get(@ApiParam(value = "CPF da pessoa", required = true)
                            @PathVariable("cpf") String cpf ) {
        return Mono.justOrEmpty(AccountMapper.toAccountOutput(this.accountService.findAccountByCpf(cpf).get()));
    }

}

