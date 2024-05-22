package com.example.graphqlscaffold.service;

import com.example.graphqlscaffold.entity.read.AccountReadEntity;
import com.example.graphqlscaffold.entity.read.CustomerReadEntity;
import com.example.graphqlscaffold.entity.write.CustomerWriteEntity;
import com.example.graphqlscaffold.generated.types.Account;
import com.example.graphqlscaffold.generated.types.AccountInput;
import com.example.graphqlscaffold.generated.types.CustomerInput;
import com.example.graphqlscaffold.generated.types.GraphOut;
import com.example.graphqlscaffold.repository.read.AccountReadRepository;
import com.example.graphqlscaffold.repository.read.CustomerReadRepository;
import com.example.graphqlscaffold.repository.write.CustomerRepository;
import com.example.graphqlscaffold.specification.AccountSpecification;
import com.example.graphqlscaffold.specification.CustomerSpecification;
import com.example.graphqlscaffold.utility.CustomerMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository repository;

    private CustomerReadRepository readRepository;

    private AccountReadRepository accountReadRepository;

    public CustomerWriteEntity addCustomer(CustomerWriteEntity customerWriteEntity){
        return repository.save(customerWriteEntity);
    }

    public void addMultipleCustomer(List<CustomerWriteEntity> customerWriteEntity){
        repository.saveAll(customerWriteEntity);
    }
    public CustomerWriteEntity getCustomer(String customerId){
        return repository.findById(customerId).get();
    }

    public List<GraphOut> getAllCustomer(Optional<CustomerInput> input){
        List<GraphOut> outs = new ArrayList<>();

        var customerInput = input.orElse(new CustomerInput());
        var accountInput = null == customerInput.getAccountInput() ? new AccountInput() : customerInput.getAccountInput();
        var specification = Specification.where(
                StringUtils.isNotBlank(customerInput.getName()) ?
                        CustomerSpecification.nameContainsIgnoreCase(customerInput.getName()) :
                        null
        ).and(
                StringUtils.isNotBlank(customerInput.getAddress()) ?
                        CustomerSpecification.addressContainsIgnoreCase(customerInput.getAddress()) :
                        null
        );
        List<CustomerReadEntity> customers = readRepository.findAll(specification);

        List<String> custIds = customers.stream().map(c -> c.getId()).collect(Collectors.toList());

        var accountSpecification = Specification.where(
                accountInput.getActive() != null ?
                        AccountSpecification.isActive(customerInput.getAccountInput().getActive()) :
                        null
                ).and(
                accountInput.getBankAccountType() != null?
                        AccountSpecification.accountTypeContainsIgnoreCase(customerInput.getAccountInput().getBankAccountType().toString()) :
                        null
                ).and(AccountSpecification.joinCustomer(custIds));


                List<AccountReadEntity> all = accountReadRepository.findAll(accountSpecification);

        Map<String, List<AccountReadEntity>> accountGroup = all.stream()
                .collect(groupingBy(AccountReadEntity::getCustomer_id));


        for(String cid : custIds){
            CustomerReadEntity customer = customers.stream().filter(c -> c.getId().equalsIgnoreCase(cid)).collect(Collectors.toList()).get(0);

            GraphOut graphOut = new GraphOut();
            graphOut.setId(cid);
            graphOut.setAddress(customer.getAddress());
            graphOut.setName(customer.getName());

            List<AccountReadEntity> accountReadEntities = accountGroup.get(cid);
            if(null != accountReadEntities) {
                List<Account> accountList = accountReadEntities.stream().map(CustomerMapper::mapToAccount).collect(Collectors.toList());
                graphOut.setAccounts(accountList);
                outs.add(graphOut);
            }
        }

        return outs;
    }
}
