package com.lourenco.gestor_contas.inputOutPut.person;

import com.lourenco.gestor_contas.tools.validators.Cpf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@ApiModel(value = "Person", description = "Dados necessários para cadastrar uma pessoa")
public class PersonInput implements Serializable {

    private static final long serialVersionUID = 4418434361829171667L;

    @ApiModelProperty(notes = "Nome da pessoa")
    @NotNull(message = "O nome não pode ser nulo.")
    @NotBlank(message = "O nome não pode ser nulo.")
    @Size(min = 3, max = 35)
    private String name;

    @ApiModelProperty(notes = "Sobrenome da pessoa")
    @NotBlank(message = "O sobrenome não pode ser nulo.")
    @NotNull(message = "O sobrenome não pode ser nulo.")
    @Size(min = 3, max = 35)
    private String lastName;

    @Cpf(message = "Favor informar um cpf valido")
    @NotBlank(message = "O cpf da pessoa não pode ser nulo.")
    private String cpf;
}
