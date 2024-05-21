package com.example.graphqlscaffold.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hello {
    private String text;
    private int randomNumber;
}
