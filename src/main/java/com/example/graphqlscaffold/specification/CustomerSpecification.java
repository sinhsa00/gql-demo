package com.example.graphqlscaffold.specification;

import com.example.graphqlscaffold.entity.read.CustomerReadEntity;
import com.example.graphqlscaffold.generated.types.CustomerInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification extends BaseSpecification {
    public static final String FIELD_NAME = "name";
    public static final String FIELD_ADDRESS = "address";

    private static Specification<CustomerReadEntity> nameContainsIgnoreCase(String keyword) {


        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(FIELD_NAME)),
                        contains(keyword.toLowerCase())
                );
    }

    private static Specification<CustomerReadEntity> addressContainsIgnoreCase(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(FIELD_ADDRESS)),
                        contains(keyword.toLowerCase())
                );
    }

    public static Specification<CustomerReadEntity> getCustomerSpecification(CustomerInput customerInput) {
        var specification = Specification.where(
                StringUtils.isNotBlank(customerInput.getName()) ?
                        nameContainsIgnoreCase(customerInput.getName()) :
                        null
        ).and(
                StringUtils.isNotBlank(customerInput.getAddress()) ?
                        addressContainsIgnoreCase(customerInput.getAddress()) :
                        null
        );

        return specification;
    }

}
