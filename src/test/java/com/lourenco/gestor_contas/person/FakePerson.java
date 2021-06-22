package com.lourenco.gestor_contas.person;

import com.github.javafaker.Faker;
import com.lourenco.gestor_contas.dal.Person;
import com.lourenco.gestor_contas.inputOutPut.person.PersonInput;
import com.lourenco.gestor_contas.inputOutPut.person.PersonOutput;

import java.util.ArrayList;
import java.util.List;

public class FakePerson {

    public static Person generate() {
        final var faker = new Faker();
        final var person = new  Person();
        person.setCpf("820.272.350-78");
        person.setName(faker.funnyName().name());
        person.setLastName(faker.funnyName().name());
        return person;
    }

    public static PersonInput generatePersonInput() {
        final var faker = new Faker();
        final var personInput = new  PersonInput();
        personInput.setCpf("820.272.350-78");
        personInput.setName(faker.funnyName().name());
        personInput.setLastName(faker.funnyName().name());
        return personInput;
    }

    public static PersonOutput generatePersonOutput() {
        final var person = generate();
        final var personOutput = new  PersonOutput();
        personOutput.setCpf(person.getCpf());
        personOutput.setName(person.getName());
        personOutput.setLastName(person.getLastName());
        return personOutput;
    }

    public static List<Person> generatePersonList() {
        List<Person> list = new ArrayList<>();
        list.add(generate());
        return list;
    }
}
