package com.lourenco.gestor_contas.module.service;


import com.lourenco.gestor_contas.dal.Person;
import com.lourenco.gestor_contas.inputOutPut.PersonInput;
import com.lourenco.gestor_contas.inputOutPut.PersonOutput;
import com.lourenco.gestor_contas.module.PersonRepository;
import com.lourenco.gestor_contas.module.mapper.PersonMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {


    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonOutput create(PersonInput personInput) throws Exception {
        validateCpfAlreadyExists(personInput.getCpf());
        final var person = PersonMapper.to(personInput);
        return PersonMapper.toPersonOutput(this.personRepository.save(person));
    }


    private void validateCpfAlreadyExists(String cpf) throws Exception {
        Optional<Person> person = this.personRepository.findByCpf(cpf);
        if (person.isPresent()) {
            throw new Exception("Já existe um resgistro para este CPF");
        }
    }
}
