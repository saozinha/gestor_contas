package com.lourenco.gestor_contas.module.account.mapper;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Person;
import com.lourenco.gestor_contas.inputOutPut.account.AccountInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountOutput;

public class AccountMapper {

    public static AccountOutput toAccountOutput(Account account) {
        final var accountOutput = new AccountOutput();
        accountOutput.setId(account.getId());
        accountOutput.setNumberAccount(account.getNumberAccount());
        accountOutput.setAgency(account.getAgency());
        accountOutput.setCpf(account.getPerson().getCpf());
        accountOutput.setTypeAccount(account.getTypeAccount());
        accountOutput.setBalance(account.getBalance());
        accountOutput.setCreatedAt(account.getCreatedAt());
        accountOutput.setUpdatedAt(account.getUpdatedAt());
        return accountOutput;
    }

    public static Account to(AccountInput accountInput, Person person) {
        final var account = new Account();
        account.setAgency(accountInput.getAgency());
        account.setNumberAccount(accountInput.getNumberAccount());
        account.setTypeAccount(accountInput.getTypeAccount());
        account.setPerson(person);
        account.setBalance(accountInput.getBalance());
        return account;
    }
}
