package com.example.graphqlscaffold.gql.resolvers;

import com.example.graphqlscaffold.generated.DgsConstants;
import com.example.graphqlscaffold.generated.types.AccountInput;
import com.example.graphqlscaffold.generated.types.Customer;
import com.example.graphqlscaffold.generated.types.CustomerInput;
import com.example.graphqlscaffold.service.CustomerService;
import com.example.graphqlscaffold.utility.CustomerMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
@DgsComponent
public class CustomerResolver {
    private CustomerService service;
    @DgsData(
            parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.Customers
    )
    public List<Customer> getCustomers(DataFetchingEnvironment dataFetchingEnvironment
            , @InputArgument Optional<CustomerInput> customerInput) {
        return service.getAllCustomer(customerInput)
                .stream().map(CustomerMapper::mapToCustomer)
                .collect(Collectors.toList());
    }
}
