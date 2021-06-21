package com.lourenco.gestor_contas.module.account.service;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.inputOutPut.TransferInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountInput;

import java.util.List;

public interface AccountService {

    Account create(AccountInput accountInput);

    Account findAccountByCpf(String cpf);

    Account findById(String idAccount);

    Statement deposit(TransferInput transferInput);

    Account findByNumberAccount(String numberAccount);

    List<Statement> findStatementByCpf(String cpf);

    Statement transferToAnotherAccount(TransferInput transferInput);
}
