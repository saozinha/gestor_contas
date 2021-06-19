package com.lourenco.gestor_contas.dal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "conta")
public class Account {

    @Id
    private String id;

    @Field(name = "typeAccount")
    private String typeAccount;

    @Field(name = "agency")
    private String agency;

    @Field(name = "numberAccount")
    private String numberAccount;

    @Field(name = "person")
    private Person person;

    @Field(name = "balance")
    private Double balance;
}
