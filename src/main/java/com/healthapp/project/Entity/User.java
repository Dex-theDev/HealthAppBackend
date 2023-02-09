package com.healthapp.project.Entity;

public class User {
    private final String firstName;
    private final String lastName;
    private int idNumber;

    public User(String firstName,String lastName, int idNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
    }
}
