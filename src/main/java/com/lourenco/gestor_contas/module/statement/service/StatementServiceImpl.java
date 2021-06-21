package com.lourenco.gestor_contas.module.statement.service;

import com.lourenco.gestor_contas.dal.Account;
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
    public Statement setStatementAccount(ActionAccount actionAccount, Account account, Double balance) {
        final var statement = new Statement();
        statement.setActionAccount(actionAccount);
        statement.setNumberAccount(account.getNumberAccount());
        statement.setAgency(account.getAgency());
        statement.setCpf(account.getPerson().getCpf());
        statement.setName(account.getPerson().getName());
        statement.setBalance(balance);
        statement.setBalanceCurrent(account.getBalance());
        return this.repository.save(statement);
    }

    @Override
    public List<Statement> findByNumberAccount(Account account) { ;
        return repository.findByNumberAccount(account.getNumberAccount());
    }
}
