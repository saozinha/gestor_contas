package com.lourenco.gestor_contas.inputOutPut.account;

import org.springframework.data.annotation.Id;

public class AccountOutput extends AccountInput {
    @Id
    private String id;
}
