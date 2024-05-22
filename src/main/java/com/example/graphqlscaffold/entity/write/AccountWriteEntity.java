package com.example.graphqlscaffold.entity.write;

import com.example.graphqlscaffold.entity.BankAccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "account")
public class AccountWriteEntity {
    @Id
    private String id;
    private Double balance;

    @Enumerated(EnumType.STRING)
    private BankAccountType bankAccountType;
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerWriteEntity customer;
}
