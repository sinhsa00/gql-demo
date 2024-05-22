package com.example.graphqlscaffold.utility;

import com.example.graphqlscaffold.entity.BankAccountType;
import com.example.graphqlscaffold.entity.read.AccountReadEntity;
import com.example.graphqlscaffold.entity.read.CustomerReadEntity;
import com.example.graphqlscaffold.entity.write.AccountWriteEntity;
import com.example.graphqlscaffold.generated.types.Account;
import com.example.graphqlscaffold.generated.types.AccountType;
import com.example.graphqlscaffold.generated.types.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerReadEntity customerReadEntity){
        List<AccountWriteEntity> accountEntities = null;//customerReadEntity.getAccountEntities();
        var accountList = accountEntities.stream().map(CustomerMapper::mapToAccount1).collect(Collectors.toList());

        return Customer.newBuilder()
                .id(customerReadEntity.getId())
                .name(customerReadEntity.getName())
                .address(customerReadEntity.getAddress())
                .accounts(accountList)
                .build();
    }

    public static Account mapToAccount(AccountReadEntity accountEntity){
        BankAccountType bankAccountType = accountEntity.getBankAccountType();
        AccountType.valueOf(bankAccountType.toString());
        return Account.newBuilder()
                .id(accountEntity.getId())
                .accountType(AccountType.valueOf(bankAccountType.toString()))
                .active(accountEntity.isActive())
                .balance(accountEntity.getBalance())
                .build();

    }


    public static Account mapToAccount1(AccountWriteEntity accountWriteEntity){
        BankAccountType bankAccountType = accountWriteEntity.getBankAccountType();
        AccountType.valueOf(bankAccountType.toString());
        return Account.newBuilder()
                .id(accountWriteEntity.getId())
                .accountType(AccountType.valueOf(bankAccountType.toString()))
                .active(accountWriteEntity.isActive())
                .balance(accountWriteEntity.getBalance())
                .build();

    }
}
