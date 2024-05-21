package com.example.graphqlscaffold.temporary;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TempConfig {
    @Bean
    public Faker getFaker(){
        return new Faker();
    }
}
