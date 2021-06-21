package com.lourenco.gestor_contas.module.statement.repository;


import com.lourenco.gestor_contas.dal.Statement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends MongoRepository<Statement, String> {

    @Query(value="{ 'accountDepositTransfer.balanceInputPayer.cpf' : ?0 }")
    List<Statement> findByAccountDepositTransferByBalanceInputPayerByCpf(String cpf);
}
