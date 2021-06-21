package com.lourenco.gestor_contas.dal;

import com.lourenco.gestor_contas.enums.TypeAccount;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "conta")
@Builder
public class Account extends BaseEntity {

    @Id
    private String id;

    @Field(name = "typeAccount")
    @Enumerated(EnumType.STRING)
    private TypeAccount typeAccount;

    @Field(name = "agency")
    private String agency;

    @Field(name = "numberAccount")
    private String numberAccount;

    @Field(name = "person")
    private Person person;

    @Field(name = "balance")
    private Double balance;
}
