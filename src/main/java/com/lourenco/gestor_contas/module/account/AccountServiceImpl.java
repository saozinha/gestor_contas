package com.lourenco.gestor_contas.module.account;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.inputOutPut.account.AccountInput;
import com.lourenco.gestor_contas.inputOutPut.account.AccountOutput;
import com.lourenco.gestor_contas.module.person.service.PersonService;
import com.lourenco.gestor_contas.tools.exceptions.ConflictException;
import com.lourenco.gestor_contas.tools.exceptions.NotBalanceAcceptableException;
import com.lourenco.gestor_contas.tools.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PersonService personService;

    @Autowired
    private AccountRepository repository;

    @Override
    public AccountOutput create(AccountInput accountInput) {
        final var person = this.personService.findByCpf(accountInput.getCpf()).get();
        this.repository.findByPerson(person).ifPresent(account -> {
            throw new ConflictException("Já existe uma conta cadastrada para o CPF "+ account.getPerson().getCpf());
        });
        validateBalance(accountInput.getBalance());
        final var account = AccountMapper.to(accountInput, person);
        return AccountMapper.toAccountOutput(this.repository.save(account));
    }

    @Override
    public Optional<Account> findAccountByCpf(String cpf) {
        final var person = this.personService.findByCpf(cpf).get();
        return  Optional.ofNullable(this.repository.findByPerson(person).orElseThrow(() -> new NotFoundException("Não foi encontrado um registro para o CPF " + cpf)));
    }

    private void validateBalance(Double balance) {
        if(balance <= 0) throw new NotBalanceAcceptableException("O saldo informado não é aceitavel, informe um valor");
    }
}
