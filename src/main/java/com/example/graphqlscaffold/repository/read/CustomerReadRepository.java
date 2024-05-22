package com.example.graphqlscaffold.repository.read;

import com.example.graphqlscaffold.entity.read.CustomerReadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReadRepository extends JpaRepository<CustomerReadEntity, String>,
        JpaSpecificationExecutor<CustomerReadEntity> {
}
