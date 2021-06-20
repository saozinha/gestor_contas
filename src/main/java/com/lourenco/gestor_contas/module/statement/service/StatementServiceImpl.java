package com.lourenco.gestor_contas.module.statement.service;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.enums.ActionAccount;
import com.lourenco.gestor_contas.module.statement.repository.StatementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class StatementServiceImpl implements  StatementService {

    private StatementRepository repository;

    @Override
    public Statement setStatementAccount(ActionAccount actionAccount, Account account, Double balance) {
        final var statement = new Statement();
        statement.setActionAccount(actionAccount);
        statement.setAccount(account);
        statement.setPerson(account.getPerson());
        statement.setBalance(balance);
        return this.repository.save(statement);
    }

    @Override
    public Statement findByAccount(Account account) { ;
        return repository.findByAccount(account);
    }
}
