package com.example.graphqlscaffold.repository;

import com.example.graphqlscaffold.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,String> {
}
