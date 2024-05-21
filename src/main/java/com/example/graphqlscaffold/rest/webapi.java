package com.example.graphqlscaffold.rest;

import com.example.graphqlscaffold.entity.AccountEntity;
import com.example.graphqlscaffold.entity.CustomerEntity;
import com.example.graphqlscaffold.repository.AccountRepository;
import com.example.graphqlscaffold.service.CustomerService;
import com.example.graphqlscaffold.temporary.TestDataGenerater;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        List<CustomerEntity> data =  dataGenerater.generateTestData();
        customerService.addMultipleCustomer(data);
    }

    @GetMapping("/acc")
    public List<AccountEntity> getacc(){
        return accountRepository.findAll();
    }

//    @GetMapping("/all")
//    public List<CustomerEntity> getCustomer(){
//        return customerService.getAllCustomer();
//    }

    @GetMapping("/{id}")
    public CustomerEntity getCustomer(@PathVariable String id){
        return customerService.getCustomer(id);
    }
}
