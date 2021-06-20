package com.lourenco.gestor_contas.module.statement.mapper;

import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.inputOutPut.statement.StatementOutput;

public class StatementMapper {

    public static StatementOutput toStatementOutput(Statement statement) {
        final var statementOutput = new StatementOutput();
        statementOutput.setActionAccount(statement.getActionAccount());
        statementOutput.setNamePerson(statement.getPerson().getName());
        statementOutput.setAccount(statement.getAccount());
        statementOutput.setBalance(statement.getBalance());
        return statementOutput;
    }
}