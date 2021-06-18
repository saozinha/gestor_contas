package com.lourenco.gestor_contas.inputOutPut;

import com.lourenco.gestor_contas.tools.Cpf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Person", description = "Dados necessários para cadastrar uma pessoa")
public class PersonInput {

    @NotBlank(message = "O nome não pode ser nulo.")
    private String name;

    @Cpf(message = "Favor informar um cpf valido")
    @ApiModelProperty(notes = "cpf da pessoa")
    @NotBlank(message = "O cpf da pessoa não pode ser nulo.")
    private String cpf;
}
