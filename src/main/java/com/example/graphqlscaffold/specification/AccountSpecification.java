package com.example.graphqlscaffold.specification;

import com.example.graphqlscaffold.entity.read.AccountReadEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class AccountSpecification extends BaseSpecification {

    public static final String FIELD_ACCOUNT_TYPE = "bankAccountType";
    public static final String FIELD_CUSTOMER_ID = "customer_id";

    public static final String FIELD_ACTIVE = "active";



        public static Specification<AccountReadEntity> accountTypeContainsIgnoreCase(String keyword) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(FIELD_ACCOUNT_TYPE)),
                    contains(keyword.toLowerCase())
            );
        };
    }

    public static Specification<AccountReadEntity> isActive(boolean active) {

        if(active){
            return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(FIELD_ACTIVE));
        }
        else {
            return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get(FIELD_ACTIVE));
        }


    }


    public static Specification<AccountReadEntity> joinCustomer(List<String> cid){
        return (root, query, criteriaBuilder) -> {
            if (cid != null && !cid.isEmpty()) {
                return root.get(FIELD_CUSTOMER_ID).in(cid);
            }
            return null;
        };
    }



}
