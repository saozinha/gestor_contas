package com.lourenco.gestor_contas.module;


import com.lourenco.gestor_contas.inputOutPut.PersonInput;
import com.lourenco.gestor_contas.inputOutPut.PersonOutput;
import com.lourenco.gestor_contas.module.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/person")
@Api(value = "Person API", consumes = "application/json charset=utf-8")
public class PersonController {


    @Autowired
    private PersonService personService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutput create(
            @ApiParam(value = "Objeto necessario para registrar uma pessoa", required = true)
            @Valid @RequestBody final PersonInput personInput) throws Exception {
        return this.personService.create(personInput);
    }
}
