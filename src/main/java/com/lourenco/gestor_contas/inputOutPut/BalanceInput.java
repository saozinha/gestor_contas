package com.lourenco.gestor_contas.inputOutPut;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "Balance", description = "Dados necessários para manipular saldo de uma conta")
public class BalanceInput implements Serializable {

    private static final long serialVersionUID = 62243115072916667L;

    @ApiModelProperty(notes = "Nome da pessoa")
    @NotNull(message = "O nome não pode ser nulo.")
    private String name;

    @ApiModelProperty(notes = "CPF da pessoa")
    @NotNull(message = "O CPF não pode ser nulo.")
    private String cpf;

    @ApiModelProperty(notes = "Número da agência")
    @NotNull(message = "O número da agência não pode ser nulo.")
    private String agency;


    @ApiModelProperty(notes = "Numero da conta")
    @NotNull(message = "O número da conta não pode ser nulo.")
    private String numberAccount;

    @ApiModelProperty(notes = "Valor para Depositar/Tranferir")
    @NotNull(message = "O valor não pode ser nulo.")
    private Double balance;
}
