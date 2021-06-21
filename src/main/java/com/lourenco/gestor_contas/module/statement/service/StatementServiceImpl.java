package com.lourenco.gestor_contas.module.statement.service;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.enums.ActionAccount;
import com.lourenco.gestor_contas.inputOutPut.BalanceInput;
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
    public Statement setStatementAccount(ActionAccount actionAccount, BalanceInput balanceInput, Double balanceCurrent) {
        final var statement = new Statement();
        statement.setActionAccount(actionAccount);
        // Dados de quem fez transfer/deposit
        statement.setNumberAccount(balanceInput.getNumberAccountOfPayer());
        statement.setAgency(balanceInput.getAgencyOfPayer());
        statement.setCpfOfPayer(balanceInput.getCpfOfPayee());
        statement.setNameOfPayer(balanceInput.getNameOfPayer());
        statement.setBalance(balanceInput.getBalance());
        statement.setBalanceCurrent(balanceCurrent);
        return this.repository.save(statement);
    }

    @Override
    public List<Statement> findByNumberAccount(Account account) { ;
        return repository.findByNumberAccount(account.getNumberAccount());
    }

}
