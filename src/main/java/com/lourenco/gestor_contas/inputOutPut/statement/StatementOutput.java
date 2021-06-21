package com.lourenco.gestor_contas.inputOutPut.statement;

import com.lourenco.gestor_contas.enums.ActionAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class StatementOutput  {

    private String id;

    private String agency;

    private String numberAccount;

    private Double balanceCurrent;

    private String cpf;

    private String name;

    private ActionAccount actionAccount;

    private Double balance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
