package com.example.joper.myfirstapp.movies.models;

/**
 * Created by joper on 26/01/17.
 */

public class User {

    String userName;
    String userLastName;
    String userCity;
    int userAge;

    public User() {
        this.userName = "";
        this.userLastName = "";
        this.userCity = "";
        this.userAge = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
}
