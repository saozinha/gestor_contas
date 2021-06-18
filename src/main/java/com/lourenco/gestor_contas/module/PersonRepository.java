package com.lourenco.gestor_contas.module;

import com.lourenco.gestor_contas.dal.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    Optional<Person>  findByCpf(String cpf);
}
