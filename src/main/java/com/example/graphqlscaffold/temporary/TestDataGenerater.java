package com.example.graphqlscaffold.temporary;

import com.example.graphqlscaffold.entity.BankAccountType;
import com.example.graphqlscaffold.entity.write.AccountWriteEntity;
import com.example.graphqlscaffold.entity.write.CustomerWriteEntity;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TestDataGenerater {
    private Faker faker;
    public List<CustomerWriteEntity> generateTestData(){
        var customerList = new ArrayList<CustomerWriteEntity>();
        for(int i=0; i<10; i++){

            int numOfAccount = faker.random().nextInt(1,3);
            var accounts = new ArrayList<AccountWriteEntity>();
            for(int j=0;j<numOfAccount;j++) {
                AccountWriteEntity a = new AccountWriteEntity();
                a.setId(UUID.randomUUID().toString());
                a.setBalance(faker.random().nextDouble(5000, 10000));
                a.setBankAccountType(randomAccountType());
                a.setActive(faker.bool().bool());
                accounts.add(a);
            }
            CustomerWriteEntity c = new CustomerWriteEntity();
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
