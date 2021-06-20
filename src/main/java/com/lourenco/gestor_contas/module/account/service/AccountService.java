package com.lourenco.gestor_contas.module.account.service;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.inputOutPut.BalanceInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountInput;

public interface AccountService {

    Account create(AccountInput accountInput);

    Account findAccountByCpf(String cpf);

    Account findById(String idAccount);

    Account deposit(BalanceInput balanceInput);

    Account findByNumberAccount(String numberAccount);

    Statement findStatementByCpf(String cpf);
}
