package com.healthapp.project.Service;

import com.healthapp.project.DAO.UserDAO;
import com.healthapp.project.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserService {

    @Autowired
 private UserDAO userDAO;

    //the api probably wont need this but im just throwing it here for now, just to have a method

    public Collection<User> getAllUsers(){
        return userDAO.getAllUsers();
    }
}
