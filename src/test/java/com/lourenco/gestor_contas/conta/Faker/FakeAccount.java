package com.lourenco.gestor_contas.conta.Faker;

import com.github.javafaker.Faker;
import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import com.lourenco.gestor_contas.enums.TypeAccount;
import com.lourenco.gestor_contas.inputOutPut.BalanceInput;
import com.lourenco.gestor_contas.inputOutPut.TransferInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountInput;
import com.lourenco.gestor_contas.module.account.mapper.AccountMapper;
import com.lourenco.gestor_contas.person.FakePerson;

public class FakeAccount {

    private static Faker faker = new Faker();

    public static AccountInput generateAccountInput() {
        final var accountInput = new  AccountInput();
        accountInput.setCpf("820.272.350-78");
        accountInput.setAgency(faker.number().digits(4));
        accountInput.setNumberAccount(faker.number().digits(6));
        accountInput.setTypeAccount(TypeAccount.CORRENTE);
        return accountInput;
    }

    public static Account generate() {
        final var accountInput = generateAccountInput();
        final var person = FakePerson.generate();
        return AccountMapper.to(accountInput, person);
    }

    public static TransferInput generateTransferInput() {
        final var transferInput = TransferInput.builder()
                .balanceInputPayer(generateBalanceInput("820.272.350-78"))
                .balanceInputPayee(generateBalanceInput("395.858.660-05"));
        return transferInput.build();
    }

    public static BalanceInput generateBalanceInput(String cpf) {
        final var balanceInput = new BalanceInput();
        balanceInput.setName(faker.funnyName().name());
        balanceInput.setCpf(cpf);
        balanceInput.setBalance(faker.random().nextDouble());
        return balanceInput;
    }


    public static Statement generateStatement() {
        var statement = new Statement();
        statement.setTransferInput(generateTransferInput());
        return statement;
    }
}
