package com.example.graphqlscaffold.rest;

import com.example.graphqlscaffold.entity.write.AccountWriteEntity;
import com.example.graphqlscaffold.entity.write.CustomerWriteEntity;
import com.example.graphqlscaffold.repository.write.AccountRepository;
import com.example.graphqlscaffold.service.CustomerService;
import com.example.graphqlscaffold.temporary.TestDataGenerater;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class webapi {
    private CustomerService customerService;
    private TestDataGenerater dataGenerater;

    private AccountRepository accountRepository;

    @GetMapping
    public void createCustomer(){
        List<CustomerWriteEntity> data =  dataGenerater.generateTestData();
        customerService.addMultipleCustomer(data);
    }

    @GetMapping("/acc")
    public List<AccountWriteEntity> getacc(){
        return accountRepository.findAll();
    }


    @GetMapping("/{id}")
    public CustomerWriteEntity getCustomer(@PathVariable String id){
        return customerService.getCustomer(id);
    }
}
