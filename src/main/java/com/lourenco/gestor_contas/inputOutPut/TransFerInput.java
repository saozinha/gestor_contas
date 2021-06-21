package com.lourenco.gestor_contas.inputOutPut;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransFerInput {
    private String numberAccount;
    private String CpfPersonReceiver;
    private Double balance;
    LocalDateTime dtRegister = LocalDateTime.now();
}
