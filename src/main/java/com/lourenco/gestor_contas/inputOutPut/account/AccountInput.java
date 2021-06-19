package com.lourenco.gestor_contas.inputOutPut.account;

import com.lourenco.gestor_contas.dal.Person;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Account", description = "Dados necessários para cadastrar uma conta")
public class AccountInput {

    @Field(name = "agency")
    private String agency;

    @Field(name = "numberAccount")
    private String numberAccount;

    @Field(name = "cpf")
    private String cpf;

    @Field(name = "balance")
    private Double balance;

    @Field(name = "typeAccount")
    private String typeAccount;
}
