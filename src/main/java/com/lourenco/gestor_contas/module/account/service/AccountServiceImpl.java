package com.lourenco.gestor_contas.module.account.service;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.enums.ActionAccount;
import com.lourenco.gestor_contas.inputOutPut.BalanceInput;
import com.lourenco.gestor_contas.inputOutPut.TransferInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountInput;
import com.lourenco.gestor_contas.module.account.mapper.AccountMapper;
import com.lourenco.gestor_contas.module.account.repository.AccountRepository;
import com.lourenco.gestor_contas.module.person.service.PersonService;
import com.lourenco.gestor_contas.module.statement.service.StatementService;
import com.lourenco.gestor_contas.tools.exceptions.BadRequestException;
import com.lourenco.gestor_contas.tools.exceptions.ConflictException;
import com.lourenco.gestor_contas.tools.exceptions.NotBalanceAcceptableException;
import com.lourenco.gestor_contas.tools.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PersonService personService;

    @Autowired
    private StatementService statementService;

    @Autowired
    private AccountRepository repository;

    public AccountServiceImpl(PersonService personService, StatementService statementService, AccountRepository repository) {
        this.personService = personService;
        this.statementService = statementService;
        this.repository = repository;
    }

    @Override
    public Account create(AccountInput accountInput) {
        final var person = this.personService.findByCpf(accountInput.getCpf());
        this.repository.findByPerson(person).ifPresent(account -> {
            throw new ConflictException("Já existe uma conta cadastrada para o CPF "+ account.getPerson().getCpf());
        });
        final var account = AccountMapper.to(accountInput, person);
        validateBalance(ActionAccount.CREATE , account.getBalance());
        this.repository.save(account);
        final var transferInput = this.addTransferInput(account);
        this.registerStatement(ActionAccount.DEPOSIT, transferInput);
        return account;
    }

    private TransferInput addTransferInput(Account account) {
        BalanceInput balanceInputPayer =  new BalanceInput();
        balanceInputPayer.setCpf(account.getPerson().getCpf());
        balanceInputPayer.setName(account.getPerson().getName());
        balanceInputPayer.setNumberAccount(account.getNumberAccount());
        balanceInputPayer.setAgency(account.getAgency());
        balanceInputPayer.setBalance(account.getBalance());
        return TransferInput.builder().balanceInputPayer(balanceInputPayer).build();
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
    public Statement deposit(TransferInput transferInput) {
        final var account = this.findAccountByCpf(transferInput.getBalanceInputPayer().getCpf());
        validateBalance(ActionAccount.DEPOSIT , transferInput.getBalanceInputPayer().getBalance());
        Double newBalance = account.getBalance() + transferInput.getBalanceInputPayer().getBalance();
        account.setBalance(newBalance);
        this.repository.save(account);
        return this.registerStatement(ActionAccount.DEPOSIT, transferInput);
    }

    @Override
    public Account findByNumberAccount(String numberAccount) {
        return this.repository.findByNumberAccount(numberAccount).orElseThrow(() -> new NotFoundException("Não foi encontrada uma conta com o número " + numberAccount));
    }

    @Override
    public List<Statement> findStatementByCpf(String cpf) {
        final var account = this.findAccountByCpf(cpf);
        return this.statementService.findByTransferInput(cpf);
    }

    @Override
    public Statement transferToAnotherAccount(TransferInput transferInput) {
        final var accountOfPayer = this.findByNumberAccount(transferInput.getBalanceInputPayer().getNumberAccount());
        // validar o saldo do titular
        this.validateIfCanTransfer(accountOfPayer.getBalance() , transferInput.getBalanceInputPayer().getBalance() );
        // subtrair o valor da conta do titular
        Double balanceCurrent = accountOfPayer.getBalance() - transferInput.getBalanceInputPayer().getBalance();
        accountOfPayer.setBalance(balanceCurrent);
        this.repository.save(accountOfPayer);

        // Envia a tranferencia para o favorecido
          this.sendTransferToPayee(transferInput);

        // registrar o extrato da conta do titular
        return this.registerStatement(ActionAccount.TRANSFER, transferInput);

    }

    private void sendTransferToPayee(TransferInput transferInput) {
        // buscar as conta do favorecido
        final var accountReceiver = this.findAccountByCpf(transferInput.getBalanceInputPayee().getCpf());
        Double balanceReceiver = accountReceiver.getBalance()  + transferInput.getBalanceInputPayer().getBalance();
        accountReceiver.setBalance(balanceReceiver);
        this.repository.save(accountReceiver);
//        final var statementTitular = this.statementService.setStatementAccount(ActionAccount.TRANSFER, balanceInput, accountReceiver.getBalance());
        this.registerStatement( ActionAccount.TRANSFER, transferInput);
    }


    private Statement registerStatement(ActionAccount actionAccount , TransferInput transferInput) {
        final var statement = new Statement();
        statement.setTransferInput(transferInput);
        return this.statementService.setStatementAccount(actionAccount, statement);
    }


    private void validateIfCanTransfer(Double balance, Double balanceTransfer) {
        if(balance < balanceTransfer || (balance - balanceTransfer == 0))
            throw new BadRequestException("Tranferência não permitida , saldo insuficiente ! ");
    }

    private void validateBalance(ActionAccount actionAccount, Double balance) {
        if(actionAccount == ActionAccount.CREATE && (balance <= 0))
            throw new NotBalanceAcceptableException("Saldo inválido, informe um valor acima de 0 ");
        if (actionAccount == ActionAccount.DEPOSIT && balance > 2000L)
             throw new NotBalanceAcceptableException("Valor inválido! Depositos devem ser abaixo de 2000 ! ");
    }
}
