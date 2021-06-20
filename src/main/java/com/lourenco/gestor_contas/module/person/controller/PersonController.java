package com.lourenco.gestor_contas.module.person.controller;


import com.lourenco.gestor_contas.inputOutPut.person.PersonInput;
import com.lourenco.gestor_contas.inputOutPut.person.PersonOutput;
import com.lourenco.gestor_contas.module.person.mapper.PersonMapper;
import com.lourenco.gestor_contas.module.person.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
@Api(value = "Person API", consumes = "application/json charset=utf-8")
public class PersonController {

    private final PersonService personService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Utilize para registrar uma pessoa", consumes = "application/json")
    public ResponseEntity<PersonOutput> create(
            @ApiParam(value = "Objeto necessario para registrar uma pessoa", required = true)
            @Valid @RequestBody final PersonInput personInput) throws Exception {
        return ResponseEntity.ok(this.personService.create(personInput));
    }


    @GetMapping("/get/{cpf}")
    @ApiOperation(value = "Utilize para buscar uma pessoa", consumes = "application/json")
    public  ResponseEntity<PersonOutput> get(@ApiParam(value = "CPF da pessoa", required = true)
                                @PathVariable("cpf") String cpf ) {
        return ResponseEntity.ok(PersonMapper.toPersonOutput(this.personService.findByCpf(cpf)));
    }

    @GetMapping("/list")
    @ApiOperation(value = "Utilize para visualizar todas as pessoas", consumes = "application/json")
    public Flux<PersonOutput> list(){
        return Flux.fromStream(this.personService.getAll().stream())
                .subscribeOn(Schedulers.boundedElastic())
                .map(PersonMapper::toPersonOutput);
    }
}
