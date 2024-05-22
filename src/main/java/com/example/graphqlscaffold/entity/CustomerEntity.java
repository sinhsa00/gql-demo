package com.example.graphqlscaffold.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customer")
public class CustomerEntity {
    @Id
    private String id;
    private String name;
    private String address;
    @OneToMany(targetEntity = AccountEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    //@OneToMany(mappedBy = "customer", targetEntity = AccountEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccountEntity> accountEntities;
}
