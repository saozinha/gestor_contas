package com.lourenco.gestor_contas.dal;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "person")
public class Person  extends BaseEntity  {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "lastName")
    private String lastName;

    @Field(name = "cpf")
    private String cpf;

}

