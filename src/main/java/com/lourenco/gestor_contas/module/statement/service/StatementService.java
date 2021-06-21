package com.lourenco.gestor_contas.module.statement.service;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.enums.ActionAccount;

import java.util.List;

public interface StatementService {

    Statement setStatementAccount(ActionAccount deposit, Account account, Double balance);

    List<Statement> findByNumberAccount(Account account);
}
