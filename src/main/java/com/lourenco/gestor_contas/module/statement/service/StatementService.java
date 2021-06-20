package com.lourenco.gestor_contas.module.statement.service;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.enums.ActionAccount;

public interface StatementService {

    Statement setStatementAccount(ActionAccount deposit, Account account, Double balance);

    Statement findByAccount(Account account);
}
