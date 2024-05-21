package com.example.graphqlscaffold.utility;

import com.example.graphqlscaffold.entity.AccountEntity;
import com.example.graphqlscaffold.entity.BankAccountType;
import com.example.graphqlscaffold.entity.CustomerEntity;
import com.example.graphqlscaffold.generated.types.Account;
import com.example.graphqlscaffold.generated.types.AccountType;
import com.example.graphqlscaffold.generated.types.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerEntity customerEntity){
        List<AccountEntity> accountEntity = customerEntity.getAccountEntities();
        var accountList = accountEntity.stream().map(CustomerMapper::mapToAccount).collect(Collectors.toList());

        return Customer.newBuilder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .address(customerEntity.getAddress())
                .accounts(accountList)
                .build();
    }

    public static Account mapToAccount(AccountEntity accountEntity){
        BankAccountType bankAccountType = accountEntity.getBankAccountType();
        AccountType.valueOf(bankAccountType.toString());
        return Account.newBuilder()
                .id(accountEntity.getId())
                .accountType(AccountType.valueOf(bankAccountType.toString()))
                .active(accountEntity.isActive())
                .balance(accountEntity.getBalance())
                .build();

    }
}
