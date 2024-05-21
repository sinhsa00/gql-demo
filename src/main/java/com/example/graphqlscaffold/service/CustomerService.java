package com.example.graphqlscaffold.service;

import com.example.graphqlscaffold.entity.CustomerEntity;
import com.example.graphqlscaffold.generated.types.CustomerInput;
import com.example.graphqlscaffold.repository.CustomerRepository;
import com.example.graphqlscaffold.specification.CustomerSpecification;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository repository;

    public CustomerEntity addCustomer(CustomerEntity customerEntity){
        return repository.save(customerEntity);
    }

    public void addMultipleCustomer(List<CustomerEntity> customerEntity){
        repository.saveAll(customerEntity);
    }
    public CustomerEntity getCustomer(String customerId){
        return repository.findById(customerId).get();
    }

    public List<CustomerEntity> getAllCustomer(Optional<CustomerInput> input){

        var customerInput = input.orElse(new CustomerInput());
        var specification = Specification.where(
                StringUtils.isNotBlank(customerInput.getName()) ?
                        CustomerSpecification.nameContainsIgnoreCase(customerInput.getName()) :
                        null
        ).and(
                StringUtils.isNotBlank(customerInput.getAddress()) ?
                        CustomerSpecification.addressContainsIgnoreCase(customerInput.getAddress()) :
                        null
        ).and(CustomerSpecification.active());

        return repository.findAll(specification);
    }
}
