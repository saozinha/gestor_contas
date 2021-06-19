package com.lourenco.gestor_contas.module.account;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.inputOutPut.account.AccountInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountOutput;

import java.util.Optional;

public interface AccountService {

    AccountOutput create(AccountInput accountInput);

    Optional<Account> findAccountByCpf(String cpf);
}
