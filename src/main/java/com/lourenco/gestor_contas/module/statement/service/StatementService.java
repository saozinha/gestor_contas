package com.lourenco.gestor_contas.module.statement.service;

import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.enums.ActionAccount;

import java.util.List;

public interface StatementService {

    Statement setStatementAccount(ActionAccount actionAccount, Statement statement);

    List<Statement> findByTransferInput(String cpf);
}
