package com.lourenco.gestor_contas.inputOutPut.person;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PersonOutput extends PersonInput {

    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}