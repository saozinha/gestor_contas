package com.lourenco.gestor_contas.module.account.service;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.enums.ActionAccount;
import com.lourenco.gestor_contas.inputOutPut.BalanceInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountInput;
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
        validateBalance(ActionAccount.CREATE , account.getBalance());
        this.repository.save(account);
        final var balanceInput = new BalanceInput();
        balanceInput.setCpfOfPayee(account.getPerson().getCpf());
        balanceInput.setNameOfPayer(account.getPerson().getName());
        balanceInput.setNumberAccountOfPayer(account.getNumberAccount());
        balanceInput.setAgencyOfPayer(account.getAgency());
        balanceInput.setBalance(account.getBalance());
        this.registerStatement(ActionAccount.DEPOSIT, balanceInput , account.getBalance());
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
        final var account = this.findAccountByCpf(balanceInput.getCpfOfPayee());
        validateBalance(ActionAccount.DEPOSIT , balanceInput.getBalance());
        Double newBalance = account.getBalance() + balanceInput.getBalance();
        account.setBalance(newBalance);
        this.repository.save(account);
        return this.registerStatement(ActionAccount.DEPOSIT, balanceInput , account.getBalance());
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
    public Statement transferToAnotherAccount(BalanceInput balanceInput) {
        final var accountOfPayer = this.findByNumberAccount(balanceInput.getNumberAccountOfPayer());
        // validar o saldo do titular
        this.validateIfCanTransfer(accountOfPayer.getBalance() , balanceInput.getBalance() );
        // subtrair o valor da conta do titular
        Double balanceCurrent = accountOfPayer.getBalance() - balanceInput.getBalance();
        accountOfPayer.setBalance(balanceCurrent);
        this.repository.save(accountOfPayer);
        // Envia a tranferencia para o favorecido
        this.sendTransferToPayee(balanceInput.getCpfOfPayee(), balanceInput);
        // registrar o extrato da conta do titular
        return this.registerStatement(ActionAccount.TRANSFER, balanceInput, accountOfPayer.getBalance() );
//        final var statementTitular = this.statementService.setStatementAccount(ActionAccount.TRANSFER, balanceInput, accountOfPayer.getBalance());

    }

    private void sendTransferToPayee(String cpf, BalanceInput balanceInput) {
        // buscar as conta do favorecido
        final var accountReceiver = this.findAccountByCpf(cpf);
        Double balanceReceiver = accountReceiver.getBalance()  + balanceInput.getBalance();
        accountReceiver.setBalance(balanceReceiver);
        this.repository.save(accountReceiver);
//        final var statementTitular = this.statementService.setStatementAccount(ActionAccount.TRANSFER, balanceInput, accountReceiver.getBalance());
        this.registerStatement( ActionAccount.TRANSFER, balanceInput, accountReceiver.getBalance() );
    }


    private Statement registerStatement(ActionAccount actionAccount , BalanceInput balanceInput,  Double balance) {
        return this.statementService.setStatementAccount(actionAccount, balanceInput , balance);
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
