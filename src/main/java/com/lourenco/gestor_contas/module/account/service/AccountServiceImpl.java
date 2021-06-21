package com.lourenco.gestor_contas.module.account.service;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.enums.ActionAccount;
import com.lourenco.gestor_contas.inputOutPut.BalanceInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountInput;
import com.lourenco.gestor_contas.inputOutPut.TransFerInput;
import com.lourenco.gestor_contas.module.account.mapper.AccountMapper;
import com.lourenco.gestor_contas.module.account.repository.AccountRepository;
import com.lourenco.gestor_contas.module.person.service.PersonService;
import com.lourenco.gestor_contas.module.statement.service.StatementService;
import com.lourenco.gestor_contas.tools.exceptions.BadRequestException;
import com.lourenco.gestor_contas.tools.exceptions.ConflictException;
import com.lourenco.gestor_contas.tools.exceptions.NotBalanceAcceptableException;
import com.lourenco.gestor_contas.tools.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private PersonService personService;

    private StatementService statementService;

    private AccountRepository repository;

    @Override
    public Account create(AccountInput accountInput) {
        final var person = this.personService.findByCpf(accountInput.getCpf());
        this.repository.findByPerson(person).ifPresent(account -> {
            throw new ConflictException("Já existe uma conta cadastrada para o CPF "+ account.getPerson().getCpf());
        });
        final var account = AccountMapper.to(accountInput, person);
        this.repository.save(account);
        this.statementService.setStatementAccount(ActionAccount.DEPOSIT, account, account.getBalance());
        return account;
    }

    @Override
    public Account findAccountByCpf(String cpf) {
        final var person = this.personService.findByCpf(cpf);
        return  this.repository.findByPerson(person).orElseThrow(() -> new NotFoundException("Não foi encontrada uma conta para o CPF " + cpf));
    }

    @Override
    public Account findById(String idAccount) {
        return  this.repository.findById(idAccount).orElseThrow(() -> new NotFoundException("Não foi encontrada uma conta com o ID " + idAccount));
    }

    @Override
    public Statement deposit(BalanceInput balanceInput) {
        final var account = this.findAccountByCpf(balanceInput.getCpf());
        final var statement = new Statement();
        statement.setActionAccount(ActionAccount.DEPOSIT);
        validateBalance(ActionAccount.DEPOSIT , balanceInput.getBalance());
        Double newBalance = account.getBalance() + balanceInput.getBalance();
        account.setBalance(newBalance);
        this.repository.save(account);
        return this.statementService.setStatementAccount(ActionAccount.DEPOSIT, account, balanceInput.getBalance());
    }

    @Override
    public Account findByNumberAccount(String numberAccount) {
        return this.repository.findByNumberAccount(numberAccount).orElseThrow(() -> new NotFoundException("Não foi encontrada uma conta com o número " + numberAccount));
    }

    @Override
    public List<Statement> findStatementByCpf(String cpf) {
        final var account = this.findAccountByCpf(cpf);
        return this.statementService.findByNumberAccount(account);
    }

    @Override
    public Statement transferToAnotherAccount(TransFerInput transferInput) {
        // buscar as conta do titular
        final var accountTitular = this.findByNumberAccount(transferInput.getNumberAccount());
        // validar o saldo do titular
        this.validateIfCanTransfer(accountTitular.getBalance() , transferInput.getBalance() );
        // subtrair o valor da conta do titular
        Double balanceTitular = accountTitular.getBalance()  - transferInput.getBalance();
        accountTitular.setBalance(balanceTitular);
        // registrar o extrato da conta do titular
        final var statementTitular = this.statementService.setStatementAccount(ActionAccount.TRANSFER, accountTitular, transferInput.getBalance());
        this.sendTransferToAccount(transferInput.getCpfPersonReceiver(), transferInput.getBalance());
        return statementTitular;
    }

    private void sendTransferToAccount(String cpf, Double balanceTransfer) {
        // buscar as conta do favorecido
        final var accountReceiver = this.findAccountByCpf(cpf);
        Double balanceReceiver = accountReceiver.getBalance()  + balanceTransfer;
        this.statementService.setStatementAccount(ActionAccount.DEPOSIT, accountReceiver, balanceTransfer);
    }

    private void validateIfCanTransfer(Double balance, Double balanceTransfer) {
        if(balance < balanceTransfer || (balance - balanceTransfer == 0))
            throw new BadRequestException("Tranferência não permitida , saldo insuficiente ! ");
    }

    private void validateBalance(ActionAccount actionAccount, Double balance) {
        if(balance <= 0) throw new NotBalanceAcceptableException("Saldo inválido, informe um valor acima de 0 ");
        if (actionAccount == ActionAccount.DEPOSIT && balance > 2000L)
             throw new NotBalanceAcceptableException("Valor inválido! Depositos devem ser abaixo de 2000 ! ");
    }
}
