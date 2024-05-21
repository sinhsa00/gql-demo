package com.example.graphqlscaffold.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "account")
public class AccountEntity {
    @Id
    private String id;
    private Double balance;

    @Enumerated(EnumType.STRING)
    private BankAccountType bankAccountType;
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
