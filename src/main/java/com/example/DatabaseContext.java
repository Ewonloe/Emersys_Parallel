package com.example;

import org.sql2o.Sql2o;
import org.springframework.context.annotation.*;

@Configuration
public class DatabaseContext {
    @Bean
    public Sql2o[] sql2o(){
    String dbUser = "postgres";
    String dbPassword = "benjamin";
    Sql2o[] sql2o = new Sql2o[3]; //For three different databases
    sql2o[0] = new Sql2o("jdbc:postgresql://127.0.0.1:5432/TB1",dbUser,dbPassword);
    sql2o[1] = new Sql2o("jdbc:postgresql://127.0.0.1:5432/TB2",dbUser,dbPassword);
    sql2o[2] = new Sql2o("jdbc:postgresql://127.0.0.1:5432/TB3",dbUser,dbPassword);

    return sql2o;
    }
}
