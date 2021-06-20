package com.lourenco.gestor_contas.module.person.service;

import com.lourenco.gestor_contas.dal.Person;
import com.lourenco.gestor_contas.inputOutPut.person.PersonInput;
import com.lourenco.gestor_contas.inputOutPut.person.PersonOutput;

import java.util.List;

public interface PersonService {

    PersonOutput create(PersonInput personInput) throws Exception;

    Person findByCpf(String cpf);

    List<Person> getAll();

    Person  findByid(String idPerson);
}