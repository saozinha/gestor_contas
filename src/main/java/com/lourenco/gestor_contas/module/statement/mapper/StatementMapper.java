package com.lourenco.gestor_contas.module.statement.mapper;

import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.inputOutPut.statement.StatementOutput;

public class StatementMapper {

    public static StatementOutput toStatementOutput(Statement statement) {
        final var statementOutput = new StatementOutput();
        statementOutput.setId(statement.getId());
        statementOutput.setActionAccount(statement.getActionAccount());
        statementOutput.setAgency(statement.getAgency());
        statementOutput.setCpf(statement.getCpfOfPayer());
        statementOutput.setName(statement.getNameOfPayer());
        statementOutput.setNumberAccount(statement.getNumberAccount());
        statementOutput.setBalance(statement.getBalance());
        statementOutput.setCreatedAt(statement.getCreatedAt());
        statementOutput.setBalanceCurrent(statement.getBalanceCurrent());
        return statementOutput;
    }
}