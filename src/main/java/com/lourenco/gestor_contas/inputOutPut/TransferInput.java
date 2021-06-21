package com.lourenco.gestor_contas.inputOutPut;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "transfer", description = "Dados necessários para transferir valores")
public class TransFerInput {
    private String numberAccount;
    private String CpfPersonReceiver;
    private Double balance;
    LocalDateTime dtRegister = LocalDateTime.now();
}
