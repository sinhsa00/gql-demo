package com.example.graphqlscaffold.repository;

import com.example.graphqlscaffold.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,String>,
        JpaSpecificationExecutor<CustomerEntity> {
}
