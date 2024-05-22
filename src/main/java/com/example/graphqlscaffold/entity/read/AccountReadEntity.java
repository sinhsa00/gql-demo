package com.example.graphqlscaffold.entity.read;

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
public class AccountReadEntity {
    @Id
    private String id;
    private Double balance;

    @Enumerated(EnumType.STRING)
    private BankAccountType bankAccountType;
    private boolean active;
    private String customer_id;
}
