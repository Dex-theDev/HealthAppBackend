package com.healthapp.project.Service;

import com.healthapp.project.DAO.UserDAO;
import com.healthapp.project.Entity.User;
import java.util.Collection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

   private JdbcTemplate jdbcTemplate;
   private UserDAO userDAO;

   @Autowired
   public UserService(DataSource dataSource, UserDAO userDAO){
     this.jdbcTemplate = new JdbcTemplate(dataSource);
     this.userDAO = userDAO;
   }


  public boolean isDBConnected() {
    try {
      int rowCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_dex", Integer.class);
      System.out.println(rowCount);
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();

      return false;
    }
  }
  //can probably add a method here that adds a row to make sure we're good
    public boolean canIAddARow(){
       try{
           String statement = "INSERT INTO user_dex (first_name, last_name) VALUES (?,?)";
           jdbcTemplate.update(statement, "Bo", "Katan");
           return true;
       } catch (Exception e){
           System.out.println(e.getMessage());
           e.printStackTrace();
           return false;
       }
    }
  // the api probably wont need this but im just throwing it here for now, just to have a method

  public Collection<User> getAllUsers() {
    return userDAO.getAllUsers();
  }
}
