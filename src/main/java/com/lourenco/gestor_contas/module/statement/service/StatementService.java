package com.lourenco.gestor_contas.module.statement.service;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.enums.ActionAccount;
import com.lourenco.gestor_contas.inputOutPut.BalanceInput;

import java.util.List;

public interface StatementService {

    Statement setStatementAccount(ActionAccount actionAccount, BalanceInput balanceInput, Double balance);

    List<Statement> findByNumberAccount(Account account);

}
