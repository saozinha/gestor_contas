package com.lourenco.gestor_contas.dal;

import com.lourenco.gestor_contas.inputOutPut.TransferInput;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "statement")
public class Statement  extends BaseEntity  {

    @Id
    private String id;

   @Field("accountDepositTransfer")
    private TransferInput transferInput;

    private LocalDateTime dtRegister = LocalDateTime.now();

}
