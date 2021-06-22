package com.lourenco.gestor_contas.conta;

import com.lourenco.gestor_contas.GestorContasApplication;
import com.lourenco.gestor_contas.conta.Faker.FakeAccount;
import com.lourenco.gestor_contas.module.account.mapper.AccountMapper;
import com.lourenco.gestor_contas.module.account.repository.AccountRepository;
import com.lourenco.gestor_contas.module.account.service.AccountService;
import com.lourenco.gestor_contas.person.FakePerson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;

@SpringBootTest(
        classes = GestorContasApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AccountServiceTest {

    @MockBean
    AccountService service;

    @MockBean
    AccountRepository repository;

    @Test
    public void shouldCreateAccount()  {
        var person = FakePerson.generate();
        var account = FakeAccount.generate();
        final var accountInput = FakeAccount.generateAccountInput();
        when(this.service.create(accountInput)).thenReturn(account);
        when(this.repository.save(AccountMapper.to(accountInput, person ))).thenReturn(account);

        this.service.create(accountInput);

        verify(this.service, times(1)).create(accountInput);
    }

    @Test
    public void shouldFindAccountByCpf()  {
        var person = FakePerson.generate();
        var account = FakeAccount.generate();
        final var accountInput = FakeAccount.generateAccountInput();
        when(this.service.findAccountByCpf(person.getCpf())).thenReturn(account);

        this.service.findAccountByCpf(person.getCpf());

        verify(this.service, times(1)).findAccountByCpf(person.getCpf());
    }

    @Test
    public void shouldDepositAccountByCpf()  {
        var transferInput = FakeAccount.generateTransferInput();
        var statement = FakeAccount.generateStatement();
        when(this.service.deposit(transferInput)).thenReturn(statement);

        this.service.deposit(transferInput);

        verify(this.service, times(1)).deposit(transferInput);
    }
}
