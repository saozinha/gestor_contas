package com.lourenco.gestor_contas.person;

import com.lourenco.gestor_contas.GestorContasApplication;
import com.lourenco.gestor_contas.module.person.mapper.PersonMapper;
import com.lourenco.gestor_contas.module.person.repository.PersonRepository;
import com.lourenco.gestor_contas.module.person.service.PersonService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@SpringBootTest(
        classes = GestorContasApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PersonServiceTest {

    @MockBean
    PersonService service;

    @MockBean
    PersonRepository repository;

    @Test
    public void shouldCreatePerson() throws Exception {

        var personInput = FakePerson.generatePersonInput();
        var person = FakePerson.generate();
        var personOutput = FakePerson.generatePersonOutput();

        when(this.service.create(personInput)).thenReturn(personOutput);
        when(this.repository.save(PersonMapper.to(personInput))).thenReturn(person);

        this.service.create(personInput);

        verify(this.service, times(1)).create(personInput);
    }

    @Test
    public void shouldFindPersonByCpf() throws Exception {

        var personInput = FakePerson.generatePersonInput();
        var person = FakePerson.generate();

        when(this.service.findByCpf(personInput.getCpf())).thenReturn(person);
        when(this.repository.findByCpf(personInput.getCpf())).thenReturn(Optional.of(person));

        this.service.findByCpf(personInput.getCpf());

        verify(this.service, times(1)).findByCpf(personInput.getCpf());
    }


    @Test
    public void shouldListAllPerson() throws Exception {

        var person = FakePerson.generate();

        when(this.service.getAll()).thenReturn(List.of(person));

        Assertions.assertFalse(this.service.getAll().isEmpty());

        verify(this.service, times(1)).getAll();
    }
}
