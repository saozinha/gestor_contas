package com.lourenco.gestor_contas.module.statement.service;

import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.enums.ActionAccount;
import com.lourenco.gestor_contas.module.statement.repository.StatementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class StatementServiceImpl implements  StatementService {

    private StatementRepository repository;

    @Override
    public Statement setStatementAccount(ActionAccount actionAccount, Statement statement) {
        return this.repository.save(statement);
    }

    @Override
    public List<Statement> findByTransferInput(String cpf) {
        return this.repository.findByAccountDepositTransferByBalanceInputPayerByCpf(cpf);
    }

}
