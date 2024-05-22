package com.example.graphqlscaffold.gql.resolvers;

import com.example.graphqlscaffold.generated.DgsConstants;
import com.example.graphqlscaffold.generated.types.CustomerInput;
import com.example.graphqlscaffold.generated.types.GraphOut;
import com.example.graphqlscaffold.service.CustomerService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@DgsComponent
public class CustomerResolver {
    private CustomerService service;
    @DgsData(
            parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.Customers

    )
    public List<GraphOut> getCustomers(DataFetchingEnvironment dataFetchingEnvironment
            , @InputArgument Optional<CustomerInput> customerInput) {
        List<GraphOut> allCustomer = service.getAllCustomer(customerInput);
     return allCustomer;
    }
}
