package com.healthapp.project.Service;

import com.healthapp.project.DAO.UserDAO;
import com.healthapp.project.Entity.User;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class UserService {

    private JdbcTemplate jdbcTemplate;
  @Autowired private UserDAO userDAO;

  @Autowired
  public UserService(DataSource dataSource){
     this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public boolean isDBConnected(){
      try{
          int rowCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_dex", Integer.class);
          System.out.println(rowCount);
          return true;
      } catch(Exception e){
          System.out.println(e.getMessage());
          return false;
      }
  }
  // the api probably wont need this but im just throwing it here for now, just to have a method

  public Collection<User> getAllUsers() {
    return userDAO.getAllUsers();
  }
}
