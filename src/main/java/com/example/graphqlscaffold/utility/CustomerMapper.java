package com.example.graphqlscaffold.utility;

import com.example.graphqlscaffold.entity.BankAccountType;
import com.example.graphqlscaffold.entity.read.AccountReadEntity;
import com.example.graphqlscaffold.generated.types.Account;
import com.example.graphqlscaffold.generated.types.AccountType;

public class CustomerMapper {

    public static Account mapToAccount(AccountReadEntity accountEntity) {
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
