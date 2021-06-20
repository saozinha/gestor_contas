package com.lourenco.gestor_contas.dal;

import com.lourenco.gestor_contas.enums.ActionAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "person")
public class Statement  extends BaseEntity  {

    @Id
    private String id;

    @Field(name = "account")
    private Account account;

    @Field(name = "person")
    private Person person ;

    @Field("actionAccount")
    private ActionAccount actionAccount;

    @Field("balance")
    private Double balance;
}
