package com.lourenco.gestor_contas.conta;


import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Person;
import com.lourenco.gestor_contas.enums.TypeAccount;
import com.lourenco.gestor_contas.module.account.repository.AccountRepository;
import com.lourenco.gestor_contas.module.person.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
@AllArgsConstructor
public class AccountTestMongo implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        Person person = Person.builder().name("Conceicao").lastName("Lourenco").cpf("820.272.350-78").build();
        personRepository.save(person);
        Person person2 = Person.builder().name("Jose").lastName("Raimundo").cpf("641.372.400-51").build();
        personRepository.save(person2);

        accountRepository.save(Account.builder().agency("001-1").numberAccount("02345-6").typeAccount(TypeAccount.CORRENTE).person(person).build());
        final var account1= accountRepository.findByPerson(person);

        accountRepository.save(Account.builder().agency("001-1").numberAccount("02345-7").typeAccount(TypeAccount.POUPANCA).person(person2).build());
        final var account2 = accountRepository.findByPerson(person2);
    }
}
