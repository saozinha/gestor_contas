package com.lourenco.gestor_contas.inputOutPut.statement;

import com.lourenco.gestor_contas.inputOutPut.TransferInput;
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

    private TransferInput transferInput;

    private LocalDateTime dtRegister = LocalDateTime.now();
}
