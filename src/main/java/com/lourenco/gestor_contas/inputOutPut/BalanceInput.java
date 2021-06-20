package com.lourenco.gestor_contas.inputOutPut;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "Balance", description = "Dados necessários para manipular saldo de uma conta")
public class BalanceInput implements Serializable {

    private static final long serialVersionUID = 62243115072916667L;

    private String cpf;

    private Double balance;
}
