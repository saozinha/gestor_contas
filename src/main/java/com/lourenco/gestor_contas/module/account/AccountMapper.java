package com.lourenco.gestor_contas.module.account;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Person;
import com.lourenco.gestor_contas.inputOutPut.account.AccountInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountOutput;

public class AccountMapper {

    public static AccountOutput toAccountOutput(Account account) {
        final var accountOutput = new AccountOutput();
        accountOutput.setNumberAccount(account.getNumberAccount());
        accountOutput.setAgency(account.getAgency());
        accountOutput.setBalance(account.getBalance());
        accountOutput.setCpf(account.getPerson().getCpf());
        accountOutput.setTypeAccount(account.getTypeAccount());
        return accountOutput;
    }

    public static Account to(AccountInput accountInput, Person person) {
        final var account = new Account();
        account.setNumberAccount(accountInput.getNumberAccount());
        account.setAgency(accountInput.getAgency());
        account.setBalance(accountInput.getBalance());
        account.setPerson(person);
        account.setTypeAccount(accountInput.getTypeAccount());
        return account;
    }
}
