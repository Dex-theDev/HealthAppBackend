package com.healthapp.project.DAO;

import com.healthapp.project.Entity.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("dummy stuff")
public class UserDAO {
  private static Map<Integer, User> users;

  static {
    users =
        new HashMap<>() {
          {
            put(1, new User("Dex", "Joseph", 1));
            put(2, new User("Jar Jar", "Binks", 2));
            put(3, new User("Kit", "Fisto", 3));
          }
        };
  }

  public Collection<User> getAllUsers() {
    return users.values();
  }
}
