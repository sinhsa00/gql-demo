package com.example.graphqlscaffold.specification;

import com.example.graphqlscaffold.entity.AccountEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification extends BaseSpecification {

    public static final String FIELD_ACCOUNT_TYPE = "bankAccountType";
    public static final String FIELD_CUSTOMER = "customer";

    public static final String FIELD_BALANCE = "balance";

    public static final String FIELD_ACTIVE = "active";

    public static final String FIELD_ID = "id";

    public static Specification<AccountEntity> accountBalanceGreterThan(double value) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(FIELD_BALANCE),
                    value
            );
        };
    }

    public static Specification<AccountEntity> accountBalanceLessThan(double value) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(FIELD_BALANCE),
                    value
            );
        };
    }

    public static Specification<AccountEntity> accountBalanceBetween(int value, int highValue) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.between(
                    root.get(FIELD_BALANCE),
                    value, highValue
            );
        };
    }

    public static Specification<AccountEntity> accountTypeContainsIgnoreCase(String keyword) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(FIELD_ACCOUNT_TYPE)),
                    contains(keyword.toLowerCase())
            );
        };
    }

    public static Specification<AccountEntity> customerNameContainsIgnoreCase(String keyword) {
        return (root, query, criteriaBuilder) -> {
            var joinCustomer = root.join(FIELD_CUSTOMER);

            return criteriaBuilder.like(
                    criteriaBuilder.lower(joinCustomer.get(
                            CustomerSpecification.FIELD_NAME
                    )),
                    contains(keyword.toLowerCase())
            );
        };
    }

    public static Specification<AccountEntity> customerAddressContainsIgnoreCase(String keyword) {
        return (root, query, criteriaBuilder) -> {
            var joinManufacturer = root.join(FIELD_CUSTOMER);

            return criteriaBuilder.like(
                    criteriaBuilder.lower(joinManufacturer.get(
                            CustomerSpecification.FIELD_ADDRESS
                    )),
                    contains(keyword.toLowerCase())
            );
        };
    }

    public static Specification<AccountEntity> accountActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(
                    root.get(FIELD_ACTIVE),
                    isActive
            );
        };
    }

    public static Specification<AccountEntity> accountIDContainsIgnoreCase(String keyword) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(FIELD_ID)),
                    contains(keyword.toLowerCase())
            );
        };
    }

}
