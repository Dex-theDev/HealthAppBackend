package com.healthapp.project.Entity;

public class User {
  private final String firstName;
  private final String lastName;
  private int idNumber;

  public User(String firstName, String lastName, int idNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.idNumber = idNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(int idNumber) {
    this.idNumber = idNumber;
  }
}
