package com.lourenco.gestor_contas.module.person.service;


import com.lourenco.gestor_contas.dal.Person;
import com.lourenco.gestor_contas.inputOutPut.person.PersonInput;
import com.lourenco.gestor_contas.inputOutPut.person.PersonOutput;
import com.lourenco.gestor_contas.module.person.mapper.PersonMapper;
import com.lourenco.gestor_contas.module.person.repository.PersonRepository;
import com.lourenco.gestor_contas.tools.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    @Override
    public PersonOutput create(PersonInput personInput) throws Exception {
        validateCpfAlreadyExists(personInput.getCpf());
        final var person = PersonMapper.to(personInput);
        return PersonMapper.toPersonOutput(this.repository.save(person));
    }

    @Override
    public Person findByCpf(String cpf) {
        return this.repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException("Não foi encontrado um registro para o CPF " + cpf));
    }

    @Override
    public List<Person> getAll() {
        List<Person> listAllPerson = this.repository.findAll();
        if (listAllPerson.isEmpty()) new NotFoundException("Não há registros");
        return listAllPerson;
    }

    @Override
    public Person findByid(String idPerson) {
        return this.repository.findByid(idPerson).orElseThrow(() -> new NotFoundException("Não foi encontrado um registro para o ID " + idPerson));
    }


    private void validateCpfAlreadyExists(String cpf) throws Exception {
        Optional<Person> person = this.repository.findByCpf(cpf);
        if (person.isPresent()) {
            throw new Exception("Já existe um registro para o CPF "+ cpf );
        }
    }
}
