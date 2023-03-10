package com.healthapp.project.Controller;

import com.healthapp.project.Entity.User;
import com.healthapp.project.Service.UserService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {
  @Autowired private UserService userService;

  @RequestMapping(method = RequestMethod.GET)
  public Collection<User> getAllUsers() {
    return userService.getAllUsers();
  }
}
