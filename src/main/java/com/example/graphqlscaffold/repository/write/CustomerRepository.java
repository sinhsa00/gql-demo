package com.example.graphqlscaffold.repository.write;

import com.example.graphqlscaffold.entity.write.CustomerWriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerWriteEntity,String>,
        JpaSpecificationExecutor<CustomerWriteEntity> {
}
