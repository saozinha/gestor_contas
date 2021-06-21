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

    @ApiModelProperty(notes = "Agencia do pagador")
    @NotNull(message = "O CPF não pode ser nulo.")
    private String  agencyOfPayer;

    @ApiModelProperty(notes = "Numero da conta do do pagador")
    @NotNull(message = "O Numero da conta não pode ser nulo.")
    private String  numberAccountOfPayer;

    @ApiModelProperty(notes = "Nome do pagador")
    @NotNull(message = "O Nome do pagador não pode ser nulo.")
    private String nameOfPayer;

    @ApiModelProperty(notes = "CPF do favorecido")
    @NotNull(message = "O CPF do favorecido não pode ser nulo.")
    private String cpfOfPayee;

    @ApiModelProperty(notes = "Valor para Depositar/Tranferir")
    @NotNull(message = "O valor não pode ser nulo.")
    private Double balance;
}
