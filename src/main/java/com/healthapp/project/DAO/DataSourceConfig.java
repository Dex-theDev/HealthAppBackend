package com.healthapp.project.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${DB_URL_TEST:jdbc:postgres://dex:JdjDKIMLKfDoiGyL4qn4wsuOZ7n6ByjP@dpg-cg1446o2qv25u2njfrk0-a.ohio-postgres.render.com/empty_db}")
    private String url;

    @Value("${DB_USERNAME_TEST:dex}")
    private String username;

    @Value("${DB_PASSWORD_TEST:JdjDKIMLKfDoiGyL4qn4wsuOZ7n6ByjP}")
    private String password;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
