package com.lourenco.gestor_contas.module.statement.repository;


import com.lourenco.gestor_contas.dal.Account;
import com.lourenco.gestor_contas.dal.Statement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends MongoRepository<Statement, String> {
    Statement findByAccount(Account account);
}
