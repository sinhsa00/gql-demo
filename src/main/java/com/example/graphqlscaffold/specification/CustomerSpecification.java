package com.example.graphqlscaffold.specification;

import com.example.graphqlscaffold.entity.AccountEntity;
import com.example.graphqlscaffold.entity.CustomerEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification extends BaseSpecification{
    public static final String FIELD_NAME = "name";
    public static final String FIELD_ADDRESS= "address";

    public static Specification<CustomerEntity> nameContainsIgnoreCase(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(FIELD_NAME)),
                        contains(keyword.toLowerCase())
                );
    }

    public static Specification<CustomerEntity> addressContainsIgnoreCase(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(FIELD_ADDRESS)),
                        contains(keyword.toLowerCase())
                );
    }

//    public static Specification<CustomerEntity> accountActive() {
//        return (root, query, criteriaBuilder) ->
//                var joinCustomer = root.join("accounts");
//
//        return criteriaBuilder.like(
//                criteriaBuilder.lower(joinCustomer.get(
//                        CustomerSpecification.FIELD_NAME
//                )),
//                contains(keyword.toLowerCase())
//        );
//    }



}
