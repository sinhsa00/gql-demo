package com.example.graphqlscaffold.entity.write;

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
public class CustomerWriteEntity {
    @Id
    private String id;
    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name ="customer_id",referencedColumnName = "id")
    private List<AccountWriteEntity> accountEntities;
}
