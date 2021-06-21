package com.lourenco.gestor_contas.inputOutPut;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@ApiModel(value = "transfer", description = "Dados necessários para depositar/transferir valores")
@JsonPropertyOrder({
        "name",
        "cpf",
        "agency",
        "numberAccount",
        "balance"
})
public class TransferInput implements Serializable {

    @ApiModelProperty(notes = "Conta pagador")
    @NotNull(message = "Os daddos da conta do pagador não pode ser nulo.")
    private BalanceInput balanceInputPayer;

    @ApiModelProperty(notes = "Conta favorecido")
    @NotNull(message = "Os daddos da conta do favorecido não pode ser nulo.")
    private BalanceInput balanceInputPayee;

    private LocalDateTime dtRegister = LocalDateTime.now();
}
