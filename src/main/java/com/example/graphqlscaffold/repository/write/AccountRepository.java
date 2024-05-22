package com.example.graphqlscaffold.repository.write;

import com.example.graphqlscaffold.entity.write.AccountWriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountWriteEntity, String>,
        JpaSpecificationExecutor<AccountWriteEntity> {
}
