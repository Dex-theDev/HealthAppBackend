package com.healthapp.project;

import com.healthapp.project.DAO.DataSourceConfig;
import com.healthapp.project.Service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class Main {


  public static void main(String[] args) {
   SpringApplication.run(Main.class, args);

   ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
      DataSource dataSource = context.getBean(DataSource.class);
     UserService userService = new UserService(dataSource);
     System.out.print(userService.isDBConnected());

    System.out.println(System.getenv("DB_URL_TEST"));
    System.out.println(System.getenv("DB_USERNAME_TEST"));
    System.out.println(System.getenv("DB_PASSWORD_TEST"));


  }
}
