package com.lourenco.gestor_contas.module.account.repository;

import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findByPerson(Person person);

    Optional<Account> findByNumberAccount(String numberAccount);
}
