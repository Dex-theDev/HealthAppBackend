package com.healthapp.project;

import com.healthapp.project.Service.MedicationService;
import com.healthapp.project.Service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.healthapp.project"})
public class Main {

  public static void main(String[] args) throws Exception {

    ApplicationContext context = SpringApplication.run(Main.class, args);


    UserService userService = context.getBean(UserService.class);
    MedicationService medicationService = context.getBean(MedicationService.class);



  System.out.print(userService.isDBConnected());
  System.out.println(userService.canIAddARow());
  System.out.println(medicationService.getConnectionAndFillTable());
  }
}
