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
@Document(collection = "statement")
public class Statement  extends BaseEntity  {

    @Id
    private String id;

    @Field("agency")
    private String agency;

    @Field("numberAccount")
    private String numberAccount;

    @Field("balanceCurrent")
    private Double balanceCurrent;

    @Field("cpf")
    private String cpfOfPayer;

    @Field("name")
    private String nameOfPayer;

    @Field("actionAccount")
    private ActionAccount actionAccount;

    @Field("balance")
    private Double balance;
}
