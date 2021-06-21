package com.lourenco.gestor_contas.module.statement.mapper;

import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.inputOutPut.statement.StatementOutput;

public class StatementMapper {

    public static StatementOutput toStatementOutput(Statement statement) {
        final var statementOutput = new StatementOutput();
        statementOutput.setId(statement.getId());
        statementOutput.setTransferInput(statement.getTransferInput());
        statementOutput.setDtRegister(statement.getDtRegister());
        return statementOutput;
    }
}