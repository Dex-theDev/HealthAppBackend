package com.healthapp.project;

import com.healthapp.project.DAO.DataSourceConfig;
import com.healthapp.project.Service.UserService;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {"com.healthapp.project"})
public class Main {

  public static void main(String[] args) {

    ApplicationContext context = SpringApplication.run(Main.class, args);


    UserService userService = context.getBean(UserService.class);

  System.out.print(userService.isDBConnected());
  System.out.println(userService.canIAddARow());
  }
}
