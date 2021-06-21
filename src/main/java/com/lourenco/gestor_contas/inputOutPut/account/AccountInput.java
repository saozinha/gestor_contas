package com.lourenco.gestor_contas.inputOutPut.account;

import com.lourenco.gestor_contas.enums.TypeAccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "Account", description = "Dados necessários para cadastrar uma conta")
public class AccountInput implements Serializable {

    private static final long serialVersionUID = 5304275972722540032L;

    @ApiModelProperty(notes = "numero da agencia", required = true)
    @NotNull(message = "A agencia não pode ser nula.")
    private String agency;

    @ApiModelProperty(notes = "numero da conta com digito", required = true)
    @NotNull(message = "O numero da conta  não pode ser nulo.")
    private String numberAccount;

    @ApiModelProperty(notes = "numero de cpf do titular", required = true)
    @NotNull(message = "O numero de cpf do titular pode ser nulo.")
    private String cpf;

    @ApiModelProperty(notes = "Tipo da conta", required = true)
    @NotNull(message = "O Tipo não pode ser nulo.")
    private TypeAccount typeAccount;

    @ApiModelProperty(notes = "Saldo da conta", required = true)
    @NotNull(message = "O Saldo não pode ser nulo.")
    private Double balance;
}