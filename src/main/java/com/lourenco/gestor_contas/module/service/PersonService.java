package com.lourenco.gestor_contas.module.service;

import com.lourenco.gestor_contas.inputOutPut.PersonInput;
import com.lourenco.gestor_contas.inputOutPut.PersonOutput;

public interface PersonService {

    PersonOutput create(PersonInput personInput) throws Exception;
}