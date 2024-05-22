package com.example.graphqlscaffold.specification;

import com.example.graphqlscaffold.entity.read.CustomerReadEntity;
import com.example.graphqlscaffold.entity.write.CustomerWriteEntity;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification extends BaseSpecification{
    public static final String FIELD_NAME = "name";
    public static final String FIELD_ADDRESS= "address";

    public static Specification<CustomerReadEntity> nameContainsIgnoreCase(String keyword) {


        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(FIELD_NAME)),
                        contains(keyword.toLowerCase())
                );
    }

    public static Specification<CustomerReadEntity> addressContainsIgnoreCase(String keyword) {
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

    public static Specification<CustomerWriteEntity> active() {
        return (root, query, criteriaBuilder) -> {
           return criteriaBuilder.equal(root.get("accountEntities").get("active"), true);
        };
    }

}
