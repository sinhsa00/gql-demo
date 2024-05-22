package com.example.graphqlscaffold.repository.read;

import com.example.graphqlscaffold.entity.read.AccountReadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountReadRepository extends JpaRepository<AccountReadEntity, String>,
        JpaSpecificationExecutor<AccountReadEntity> {
}
