package com.lourenco.gestor_contas.module.mapper;

import com.lourenco.gestor_contas.dal.Person;
import com.lourenco.gestor_contas.inputOutPut.PersonInput;
import com.lourenco.gestor_contas.inputOutPut.PersonOutput;

import java.util.ArrayList;
import java.util.List;

public class PersonMapper {

    public static Person to(PersonInput personInput) {
        final var person = new Person();
        person.setName(personInput.getName());
        person.setCpf(personInput.getCpf());
        return person;
    }

    public static PersonOutput toPersonOutput(Person person) {
        final var personOutput = new PersonOutput();
        personOutput.setId(person.getId());
        personOutput.setName(person.getName());
        return personOutput;
    }

    public static List<PersonOutput> toListPersonOutput(List<Person> list) {
        var allPerson = new ArrayList<PersonOutput>();
        list.forEach(person -> {
            allPerson.add(toPersonOutput(person));
        });
        return allPerson;
    }
}

