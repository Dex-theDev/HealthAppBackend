package com.healthapp.project.DAO;

import javax.sql.DataSource;

import com.healthapp.project.Service.UserService;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

private final String username = System.getenv("DB_USERNAME_TEST");

private final String password = System.getenv("DB_PASSWORD_TEST");
  @Bean
  public DataSource dataSource() {
  DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl("jdbc:postgresql://dpg-cg1446o2qv25u2njfrk0-a.ohio-postgres.render.com:5432/empty_db?user=" + username +"&password=" + password +"");
    return dataSource;
  }
}
