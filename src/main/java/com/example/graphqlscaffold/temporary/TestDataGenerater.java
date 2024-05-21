package com.example.graphqlscaffold.temporary;

import com.example.graphqlscaffold.entity.AccountEntity;
import com.example.graphqlscaffold.entity.BankAccountType;
import com.example.graphqlscaffold.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TestDataGenerater {
    private Faker faker;
    public List<CustomerEntity> generateTestData(){
        var customerList = new ArrayList<CustomerEntity>();
        for(int i=0; i<10; i++){

            int numOfAccount = faker.random().nextInt(1,3);
            var accounts = new ArrayList<AccountEntity>();
            for(int j=0;j<numOfAccount;j++) {
                AccountEntity a = new AccountEntity();
                a.setId(UUID.randomUUID().toString());
                a.setBalance(faker.random().nextDouble(5000, 10000));
                a.setBankAccountType(randomAccountType());
                a.setActive(faker.bool().bool());
                accounts.add(a);
            }
            CustomerEntity c = new CustomerEntity();
            c.setId(UUID.randomUUID().toString());
            c.setName(faker.name().name());
            c.setAddress(faker.address().streetAddress(false));
            c.setAccountEntities(accounts);
            customerList.add(c);
        }
        return customerList;
    }

    private BankAccountType randomAccountType() {
        int take = new Random().nextInt(BankAccountType.values().length);
        return BankAccountType.values()[take];
    }
}
